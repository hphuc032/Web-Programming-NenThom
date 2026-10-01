package vn.hcmute.web24162095.dao.impl;

import vn.hcmute.web24162095.connection.DBConnection_24162095;
import vn.hcmute.web24162095.dao.OrderDAO_24162095;
import vn.hcmute.web24162095.model.CartItem_24162095;
import vn.hcmute.web24162095.model.Cart_24162095;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAOImpl_24162095 implements OrderDAO_24162095 {
    @Override
    public List<Cart_24162095> findOrdersByUserId(int userId, String status) {
        String sql = "SELECT * FROM Cart WHERE userId=? AND status<>'CART'"
                + (status == null ? "" : " AND status=?")
                + " ORDER BY buyDate DESC,cartId DESC";
        List<Cart_24162095> orders = new ArrayList<>();
        try (Connection connection = DBConnection_24162095.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            if (status != null) statement.setString(2, status);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) orders.add(mapOrder(result));
            }
            return orders;
        } catch (SQLException e) {
            throw new DataAccessException_24162095("Không thể tải lịch sử đơn hàng", e);
        }
    }

    @Override
    public Optional<Cart_24162095> findOrderByIdAndUserId(int cartId, int userId) {
        String sql = "SELECT * FROM Cart WHERE cartId=? AND userId=? AND status<>'CART'";
        try (Connection connection = DBConnection_24162095.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);
            statement.setInt(2, userId);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? Optional.of(mapOrder(result)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataAccessException_24162095("Không thể tải chi tiết đơn hàng", e);
        }
    }

    @Override
    public List<CartItem_24162095> findOrderItems(int cartId) {
        String sql = "SELECT ci.*,p.productName,p.images,p.stock,p.status AS productStatus "
                + "FROM CartItem ci JOIN Product p ON p.productId=ci.productId "
                + "WHERE ci.cartId=? ORDER BY ci.cartItemId";
        List<CartItem_24162095> items = new ArrayList<>();
        try (Connection connection = DBConnection_24162095.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) items.add(mapItem(result));
            }
            return items;
        } catch (SQLException e) {
            throw new DataAccessException_24162095("Không thể tải sản phẩm của đơn hàng", e);
        }
    }

    private Cart_24162095 mapOrder(ResultSet result) throws SQLException {
        Cart_24162095 order = new Cart_24162095();
        order.setCartId(result.getInt("cartId"));
        order.setUserId(result.getInt("userId"));
        Timestamp buyDate = result.getTimestamp("buyDate");
        if (buyDate != null) order.setBuyDate(buyDate.toLocalDateTime());
        order.setStatus(result.getString("status"));
        order.setReceiverName(result.getString("receiverName"));
        order.setReceiverPhone(result.getString("receiverPhone"));
        order.setShippingAddress(result.getString("shippingAddress"));
        order.setPaymentMethod(result.getString("paymentMethod"));
        order.setTotalAmount(result.getBigDecimal("totalAmount"));
        order.setNote(result.getString("note"));
        return order;
    }

    private CartItem_24162095 mapItem(ResultSet result) throws SQLException {
        CartItem_24162095 item = new CartItem_24162095();
        item.setCartItemId(result.getInt("cartItemId"));
        item.setQuantity(result.getInt("quantity"));
        item.setUnitPrice(result.getBigDecimal("unitPrice"));
        item.setProductId(result.getInt("productId"));
        item.setCartId(result.getInt("cartId"));
        item.setProductName(result.getString("productName"));
        item.setProductImage(result.getString("images"));
        item.setAvailableStock(result.getInt("stock"));
        item.setProductActive(result.getBoolean("productStatus"));
        return item;
    }
}
