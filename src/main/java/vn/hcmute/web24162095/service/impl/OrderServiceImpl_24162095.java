package vn.hcmute.web24162095.service.impl;

import vn.hcmute.web24162095.dao.OrderDAO_24162095;
import vn.hcmute.web24162095.dao.impl.OrderDAOImpl_24162095;
import vn.hcmute.web24162095.model.CartItem_24162095;
import vn.hcmute.web24162095.model.Cart_24162095;
import vn.hcmute.web24162095.model.OrderStatus_24162095;
import vn.hcmute.web24162095.service.OrderService_24162095;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl_24162095 implements OrderService_24162095 {
    private final OrderDAO_24162095 orderDAO;

    public OrderServiceImpl_24162095() {
        this(new OrderDAOImpl_24162095());
    }

    public OrderServiceImpl_24162095(OrderDAO_24162095 orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public List<Cart_24162095> getOrders(int userId, String requestedStatus) {
        requireUser(userId);
        return orderDAO.findOrdersByUserId(userId, normalizeStatusFilter(requestedStatus));
    }

    @Override
    public Optional<Cart_24162095> getOrder(int userId, int orderId) {
        requireUser(userId);
        if (orderId <= 0) return Optional.empty();
        return orderDAO.findOrderByIdAndUserId(orderId, userId);
    }

    @Override
    public List<CartItem_24162095> getOrderItems(int userId, int orderId) {
        if (getOrder(userId, orderId).isEmpty()) return List.of();
        return orderDAO.findOrderItems(orderId);
    }

    @Override
    public String normalizeStatusFilter(String requestedStatus) {
        if (requestedStatus == null || requestedStatus.isBlank()) return null;
        String candidate = requestedStatus.trim().toUpperCase();
        return OrderStatus_24162095.fromCode(candidate)
                .filter(OrderStatus_24162095::isOrderStatus)
                .map(OrderStatus_24162095::getCode)
                .orElse(null);
    }

    @Override
    public List<OrderStatus_24162095> getStatusOptions() {
        return OrderStatus_24162095.orderStatuses();
    }

    private void requireUser(int userId) {
        if (userId <= 0) throw new IllegalArgumentException("Tài khoản không hợp lệ.");
    }
}
