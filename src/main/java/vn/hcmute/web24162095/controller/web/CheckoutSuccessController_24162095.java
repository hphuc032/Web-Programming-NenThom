package vn.hcmute.web24162095.controller.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.model.Cart_24162095;

import java.io.IOException;

@WebServlet("/checkout/success")
public class CheckoutSuccessController_24162095 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Object value = session == null ? null : session.getAttribute("lastCheckoutOrder");
        if (!(value instanceof Cart_24162095 order)) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }
        request.setAttribute("order", order);
        session.removeAttribute("lastCheckoutOrder");
        request.getRequestDispatcher("/views/web/checkout-success.jsp").forward(request, response);
    }
}
