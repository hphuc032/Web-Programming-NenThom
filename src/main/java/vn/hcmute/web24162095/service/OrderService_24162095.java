package vn.hcmute.web24162095.service;

import vn.hcmute.web24162095.model.CartItem_24162095;
import vn.hcmute.web24162095.model.Cart_24162095;
import vn.hcmute.web24162095.model.OrderStatus_24162095;

import java.util.List;
import java.util.Optional;

public interface OrderService_24162095 {
    List<Cart_24162095> getOrders(int userId, String requestedStatus);
    Optional<Cart_24162095> getOrder(int userId, int orderId);
    List<CartItem_24162095> getOrderItems(int userId, int orderId);
    String normalizeStatusFilter(String requestedStatus);
    List<OrderStatus_24162095> getStatusOptions();
}
