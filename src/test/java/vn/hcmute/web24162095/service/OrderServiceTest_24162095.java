package vn.hcmute.web24162095.service;

import org.junit.jupiter.api.Test;
import vn.hcmute.web24162095.dao.OrderDAO_24162095;
import vn.hcmute.web24162095.model.CartItem_24162095;
import vn.hcmute.web24162095.model.Cart_24162095;
import vn.hcmute.web24162095.service.impl.OrderServiceImpl_24162095;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest_24162095 {
    @Test
    void acceptsOnlyWhitelistedOrderStatus() {
        FakeOrderDAO dao = new FakeOrderDAO();
        OrderService_24162095 service = new OrderServiceImpl_24162095(dao);
        service.getOrders(2, "confirmed");
        assertEquals("CONFIRMED", dao.lastStatus);
        service.getOrders(2, "HACKED");
        assertNull(dao.lastStatus);
        service.getOrders(2, "CART");
        assertNull(dao.lastStatus);
    }

    @Test
    void orderDetailRequiresBothOrderAndCurrentUser() {
        FakeOrderDAO dao = new FakeOrderDAO();
        OrderService_24162095 service = new OrderServiceImpl_24162095(dao);
        assertTrue(service.getOrder(2, 1).isPresent());
        assertTrue(service.getOrder(3, 1).isEmpty());
        assertTrue(service.getOrderItems(3, 1).isEmpty());
        assertFalse(dao.itemsQueried);
    }

    private static class FakeOrderDAO implements OrderDAO_24162095 {
        String lastStatus;
        boolean itemsQueried;

        @Override
        public List<Cart_24162095> findOrdersByUserId(int userId, String status) {
            lastStatus = status;
            return List.of();
        }

        @Override
        public Optional<Cart_24162095> findOrderByIdAndUserId(int cartId, int userId) {
            if (cartId != 1 || userId != 2) return Optional.empty();
            Cart_24162095 order = new Cart_24162095();
            order.setCartId(1);
            order.setUserId(2);
            order.setStatus("NEW");
            return Optional.of(order);
        }

        @Override
        public List<CartItem_24162095> findOrderItems(int cartId) {
            itemsQueried = true;
            return List.of();
        }
    }
}
