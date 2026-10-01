package vn.hcmute.web24162095.controller.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.service.OrderService_24162095;
import vn.hcmute.web24162095.service.impl.OrderServiceImpl_24162095;
import vn.hcmute.web24162095.util.CartWebUtil_24162095;

import java.io.IOException;

@WebServlet("/orders")
public class OrderHistoryController_24162095 extends HttpServlet {
    private final OrderService_24162095 service = new OrderServiceImpl_24162095();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectedStatus = service.normalizeStatusFilter(request.getParameter("status"));
        int userId = CartWebUtil_24162095.currentUserId(request);
        request.setAttribute("orders", service.getOrders(userId, selectedStatus));
        request.setAttribute("statusOptions", service.getStatusOptions());
        request.setAttribute("selectedStatus", selectedStatus);
        request.getRequestDispatcher("/views/web/orders.jsp").forward(request, response);
    }
}
