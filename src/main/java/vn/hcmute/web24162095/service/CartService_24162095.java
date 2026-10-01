package vn.hcmute.web24162095.service;

import vn.hcmute.web24162095.model.CartItem_24162095;
import vn.hcmute.web24162095.model.Cart_24162095;

import java.math.BigDecimal;
import java.util.List;

public interface CartService_24162095 {
    Cart_24162095 getOrCreateCart(int userId);
    List<CartItem_24162095> getItems(int userId);
    BigDecimal getTotal(int userId);
    int getTotalQuantity(int userId);
    void addProduct(int userId, int productId, int quantity);
    void updateQuantity(int userId, int productId, int quantity);
    void adjustQuantity(int userId, int productId, int delta);
    void removeProduct(int userId, int productId);
    void clearCart(int userId);
}
