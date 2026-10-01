package vn.hcmute.web24162095.dao;

import vn.hcmute.web24162095.model.CartItem_24162095;
import vn.hcmute.web24162095.model.Cart_24162095;

import java.util.List;
import java.util.Optional;

public interface OrderDAO_24162095 {
    List<Cart_24162095> findOrdersByUserId(int userId, String status);
    Optional<Cart_24162095> findOrderByIdAndUserId(int cartId, int userId);
    List<CartItem_24162095> findOrderItems(int cartId);
}
