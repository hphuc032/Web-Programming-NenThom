package vn.hcmute.web24162095.controller.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.model.Cart_24162095;
import vn.hcmute.web24162095.service.OrderService_24162095;
import vn.hcmute.web24162095.service.impl.OrderServiceImpl_24162095;
import vn.hcmute.web24162095.util.CartWebUtil_24162095;
import vn.hcmute.web24162095.util.RequestUtil_24162095;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/order/detail")
public class OrderDetailController_24162095 extends HttpServlet {
    private final OrderService_24162095 service = new OrderServiceImpl_24162095();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderId = RequestUtil_24162095.intParam(request, "id", -1);
        int userId = CartWebUtil_24162095.currentUserId(request);
        Optional<Cart_24162095> found = service.getOrder(userId, orderId);
        if (found.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy đơn hàng.");
            return;
        }
        request.setAttribute("order", found.get());
        request.setAttribute("items", service.getOrderItems(userId, orderId));
        request.getRequestDispatcher("/views/web/order-detail.jsp").forward(request, response);
    }
}
