package vn.hcmute.web24162095.dao.impl;

import vn.hcmute.web24162095.connection.DBConnection_24162095;
import vn.hcmute.web24162095.dao.CheckoutDAO_24162095;
import vn.hcmute.web24162095.model.CartItem_24162095;
import vn.hcmute.web24162095.model.Cart_24162095;
import vn.hcmute.web24162095.service.CheckoutException_24162095;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CheckoutDAOImpl_24162095 implements CheckoutDAO_24162095 {
    @Override
    public Cart_24162095 checkoutCod(int userId, String receiverName, String receiverPhone,
                                    String shippingAddress, String note) {
        Connection connection = null;
        try {
            connection = DBConnection_24162095.getConnection();
            connection.setAutoCommit(false);

            int cartId = lockActiveCart(connection, userId);
            List<CartItem_24162095> items = lockCartItemsAndProducts(connection, cartId);
            if (items.isEmpty()) throw new CheckoutException_24162095("Giỏ hàng đang trống, không thể thanh toán.");

            BigDecimal total = BigDecimal.ZERO;
            for (CartItem_24162095 item : items) {
                if (!item.isProductActive()) {
                    throw new CheckoutException_24162095("Sản phẩm “" + item.getProductName() + "” hiện không còn bán.");
                }
                if (item.getQuantity() > item.getAvailableStock()) {
                    throw new CheckoutException_24162095("Số lượng sản phẩm “" + item.getProductName()
                            + "” vượt tồn kho. Hiện chỉ còn " + item.getAvailableStock() + ".");
                }
                total = total.add(item.getSubtotal());
            }

            for (CartItem_24162095 item : items) {
                decreaseStock(connection, item);
            }
            LocalDateTime checkoutTime = LocalDateTime.now();
            markCartAsOrder(connection, cartId, userId, receiverName, receiverPhone,
                    shippingAddress, note, total);
            connection.commit();

            Cart_24162095 order = new Cart_24162095();
            order.setCartId(cartId);
            order.setUserId(userId);
            order.setBuyDate(checkoutTime);
            order.setStatus("NEW");
            order.setReceiverName(receiverName);
            order.setReceiverPhone(receiverPhone);
            order.setShippingAddress(shippingAddress);
            order.setPaymentMethod("COD");
            order.setTotalAmount(total);
            order.setNote(note);
            return order;
        } catch (CheckoutException_24162095 e) {
            rollback(connection, e);
            throw e;
        } catch (SQLException e) {
            rollback(connection, e);
            throw new DataAccessException_24162095("Không thể hoàn tất thanh toán COD", e);
        } finally {
            close(connection);
        }
    }

    private int lockActiveCart(Connection connection, int userId) throws SQLException {
        String sql = "SELECT cartId FROM Cart WHERE userId=? AND status='CART' "
                + "ORDER BY cartId DESC LIMIT 1 FOR UPDATE";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) throw new CheckoutException_24162095("Không tìm thấy giỏ hàng đang mua.");
                return result.getInt("cartId");
            }
        }
    }

    private List<CartItem_24162095> lockCartItemsAndProducts(Connection connection, int cartId)
            throws SQLException {
        String sql = "SELECT ci.cartItemId,ci.cartId,ci.productId,ci.quantity,ci.unitPrice,"
                + "p.productName,p.images,p.stock,p.status AS productStatus "
                + "FROM CartItem ci JOIN Product p ON p.productId=ci.productId "
                + "WHERE ci.cartId=? ORDER BY p.productId FOR UPDATE";
        List<CartItem_24162095> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    CartItem_24162095 item = new CartItem_24162095();
                    item.setCartItemId(result.getInt("cartItemId"));
                    item.setCartId(result.getInt("cartId"));
                    item.setProductId(result.getInt("productId"));
                    item.setQuantity(result.getInt("quantity"));
                    item.setUnitPrice(result.getBigDecimal("unitPrice"));
                    item.setProductName(result.getString("productName"));
                    item.setProductImage(result.getString("images"));
                    item.setAvailableStock(result.getInt("stock"));
                    item.setProductActive(result.getBoolean("productStatus"));
                    items.add(item);
                }
            }
        }
        return items;
    }

    private void decreaseStock(Connection connection, CartItem_24162095 item) throws SQLException {
        String sql = "UPDATE Product SET stock=stock-? WHERE productId=? AND status=1 AND stock>=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, item.getQuantity());
            statement.setInt(2, item.getProductId());
            statement.setInt(3, item.getQuantity());
            if (statement.executeUpdate() != 1) {
                throw new CheckoutException_24162095("Tồn kho vừa thay đổi. Vui lòng kiểm tra lại giỏ hàng.");
            }
        }
    }

    private void markCartAsOrder(Connection connection, int cartId, int userId,
                                 String receiverName, String receiverPhone,
                                 String shippingAddress, String note, BigDecimal total) throws SQLException {
        String sql = "UPDATE Cart SET status='NEW',buyDate=NOW(),receiverName=?,receiverPhone=?,"
                + "shippingAddress=?,paymentMethod='COD',totalAmount=?,note=? "
                + "WHERE cartId=? AND userId=? AND status='CART'";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, receiverName);
            statement.setString(2, receiverPhone);
            statement.setString(3, shippingAddress);
            statement.setBigDecimal(4, total);
            statement.setString(5, note);
            statement.setInt(6, cartId);
            statement.setInt(7, userId);
            if (statement.executeUpdate() != 1) {
                throw new CheckoutException_24162095("Giỏ hàng đã thay đổi. Vui lòng thử lại.");
            }
        }
    }

    private void rollback(Connection connection, Exception original) {
        if (connection == null) return;
        try {
            connection.rollback();
        } catch (SQLException rollbackError) {
            original.addSuppressed(rollbackError);
        }
    }

    private void close(Connection connection) {
        if (connection == null) return;
        try {
            connection.setAutoCommit(true);
        } catch (SQLException ignored) {
            // Connection is being closed immediately below.
        }
        try {
            connection.close();
        } catch (SQLException ignored) {
            // Nothing useful can be done after transaction completion.
        }
    }
}
