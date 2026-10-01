package vn.hcmute.web24162095.dao.impl;

import vn.hcmute.web24162095.connection.DBConnection_24162095;
import vn.hcmute.web24162095.dao.CartDAO_24162095;
import vn.hcmute.web24162095.model.CartItem_24162095;
import vn.hcmute.web24162095.model.Cart_24162095;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartDAOImpl_24162095 implements CartDAO_24162095 {
    @Override
    public Optional<Cart_24162095> findActiveCartByUserId(int userId) {
        String sql = "SELECT * FROM Cart WHERE userId=? AND status='CART' ORDER BY cartId DESC LIMIT 1";
        try (Connection connection = DBConnection_24162095.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? Optional.of(mapCart(result)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataAccessException_24162095("Không thể tải giỏ hàng", e);
        }
    }

    @Override
    public Cart_24162095 createCart(int userId) {
        String sql = "INSERT INTO Cart(userId,buyDate,status) VALUES (?,NOW(),'CART')";
        try (Connection connection = DBConnection_24162095.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (!keys.next()) throw new SQLException("Không nhận được cartId");
                Cart_24162095 cart = new Cart_24162095();
                cart.setCartId(keys.getInt(1));
                cart.setUserId(userId);
                cart.setStatus("CART");
                return cart;
            }
        } catch (SQLException e) {
            throw new DataAccessException_24162095("Không thể tạo giỏ hàng", e);
        }
    }

    @Override
    public List<CartItem_24162095> findItemsByCartId(int cartId) {
        String sql = "SELECT ci.*,p.productName,p.images,p.stock,p.status AS productStatus "
                + "FROM CartItem ci JOIN Product p ON ci.productId=p.productId "
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
            throw new DataAccessException_24162095("Không thể tải sản phẩm trong giỏ", e);
        }
    }

    @Override
    public Optional<CartItem_24162095> findItem(int cartId, int productId) {
        String sql = "SELECT ci.*,p.productName,p.images,p.stock,p.status AS productStatus "
                + "FROM CartItem ci JOIN Product p ON ci.productId=p.productId "
                + "WHERE ci.cartId=? AND ci.productId=?";
        try (Connection connection = DBConnection_24162095.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);
            statement.setInt(2, productId);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? Optional.of(mapItem(result)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataAccessException_24162095("Không thể tải sản phẩm trong giỏ", e);
        }
    }

    @Override
    public void addItem(int cartId, int productId, int quantity, BigDecimal unitPrice) {
        String sql = "INSERT INTO CartItem(quantity,unitPrice,productId,cartId) VALUES (?,?,?,?)";
        executeUpdate(sql, statement -> {
            statement.setInt(1, quantity);
            statement.setBigDecimal(2, unitPrice);
            statement.setInt(3, productId);
            statement.setInt(4, cartId);
        }, "Không thể thêm sản phẩm vào giỏ");
    }

    @Override
    public void updateQuantity(int cartId, int productId, int quantity) {
        String sql = "UPDATE CartItem SET quantity=? WHERE cartId=? AND productId=?";
        executeUpdate(sql, statement -> {
            statement.setInt(1, quantity);
            statement.setInt(2, cartId);
            statement.setInt(3, productId);
        }, "Không thể cập nhật số lượng");
    }

    @Override
    public void removeItem(int cartId, int productId) {
        executeUpdate("DELETE FROM CartItem WHERE cartId=? AND productId=?", statement -> {
            statement.setInt(1, cartId);
            statement.setInt(2, productId);
        }, "Không thể xóa sản phẩm khỏi giỏ");
    }

    @Override
    public void clearCart(int cartId) {
        executeUpdate("DELETE FROM CartItem WHERE cartId=?", statement -> statement.setInt(1, cartId),
                "Không thể xóa giỏ hàng");
    }

    @Override
    public BigDecimal calculateTotal(int cartId) {
        String sql = "SELECT COALESCE(SUM(quantity*unitPrice),0) total FROM CartItem WHERE cartId=?";
        try (Connection connection = DBConnection_24162095.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? result.getBigDecimal("total") : BigDecimal.ZERO;
            }
        } catch (SQLException e) {
            throw new DataAccessException_24162095("Không thể tính tổng giỏ hàng", e);
        }
    }

    @Override
    public int countItems(int cartId) {
        String sql = "SELECT COALESCE(SUM(quantity),0) totalQuantity FROM CartItem WHERE cartId=?";
        try (Connection connection = DBConnection_24162095.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? result.getInt("totalQuantity") : 0;
            }
        } catch (SQLException e) {
            throw new DataAccessException_24162095("Không thể đếm sản phẩm trong giỏ", e);
        }
    }

    private void executeUpdate(String sql, StatementBinder binder, String message) {
        try (Connection connection = DBConnection_24162095.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            binder.bind(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException_24162095(message, e);
        }
    }

    private Cart_24162095 mapCart(ResultSet result) throws SQLException {
        Cart_24162095 cart = new Cart_24162095();
        cart.setCartId(result.getInt("cartId"));
        cart.setUserId(result.getInt("userId"));
        Timestamp buyDate = result.getTimestamp("buyDate");
        if (buyDate != null) cart.setBuyDate(buyDate.toLocalDateTime());
        cart.setStatus(result.getString("status"));
        cart.setReceiverName(result.getString("receiverName"));
        cart.setReceiverPhone(result.getString("receiverPhone"));
        cart.setShippingAddress(result.getString("shippingAddress"));
        cart.setPaymentMethod(result.getString("paymentMethod"));
        cart.setTotalAmount(result.getBigDecimal("totalAmount"));
        cart.setNote(result.getString("note"));
        return cart;
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

    @FunctionalInterface
    private interface StatementBinder {
        void bind(PreparedStatement statement) throws SQLException;
    }
}
