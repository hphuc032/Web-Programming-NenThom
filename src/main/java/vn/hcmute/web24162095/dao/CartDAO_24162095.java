package vn.hcmute.web24162095.dao;

import vn.hcmute.web24162095.model.CartItem_24162095;
import vn.hcmute.web24162095.model.Cart_24162095;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CartDAO_24162095 {
    Optional<Cart_24162095> findActiveCartByUserId(int userId);
    Cart_24162095 createCart(int userId);
    List<CartItem_24162095> findItemsByCartId(int cartId);
    Optional<CartItem_24162095> findItem(int cartId, int productId);
    void addItem(int cartId, int productId, int quantity, BigDecimal unitPrice);
    void updateQuantity(int cartId, int productId, int quantity);
    void removeItem(int cartId, int productId);
    void clearCart(int cartId);
    BigDecimal calculateTotal(int cartId);
    int countItems(int cartId);
}
