package vn.hcmute.web24162095.controller.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.model.Cart_24162095;
import vn.hcmute.web24162095.model.User_24162095;
import vn.hcmute.web24162095.service.*;
import vn.hcmute.web24162095.service.impl.CartServiceImpl_24162095;
import vn.hcmute.web24162095.service.impl.CheckoutServiceImpl_24162095;
import vn.hcmute.web24162095.util.CartWebUtil_24162095;
import vn.hcmute.web24162095.util.Constant_24162095;
import vn.hcmute.web24162095.util.RequestUtil_24162095;

import java.io.IOException;

@WebServlet("/checkout")
public class CheckoutController_24162095 extends HttpServlet {
    private final CartService_24162095 cartService = new CartServiceImpl_24162095();
    private final CheckoutService_24162095 checkoutService = new CheckoutServiceImpl_24162095();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = CartWebUtil_24162095.currentUserId(request);
        if (cartService.getItems(userId).isEmpty()) {
            CartWebUtil_24162095.flash(request, "danger", "Giỏ hàng đang trống, không thể thanh toán.");
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }
        User_24162095 account = (User_24162095) request.getSession()
                .getAttribute(Constant_24162095.SESSION_ACCOUNT);
        request.setAttribute("receiverName", account.getFullname());
        request.setAttribute("receiverPhone", account.getPhone());
        showForm(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String receiverName = RequestUtil_24162095.trim(request.getParameter("receiverName"));
        String receiverPhone = RequestUtil_24162095.trim(request.getParameter("receiverPhone"));
        String shippingAddress = RequestUtil_24162095.trim(request.getParameter("shippingAddress"));
        String note = RequestUtil_24162095.trim(request.getParameter("note"));
        request.setAttribute("receiverName", receiverName);
        request.setAttribute("receiverPhone", receiverPhone);
        request.setAttribute("shippingAddress", shippingAddress);
        request.setAttribute("note", note);
        try {
            Cart_24162095 order = checkoutService.checkoutCod(
                    CartWebUtil_24162095.currentUserId(request), receiverName,
                    receiverPhone, shippingAddress, note);
            request.getSession().setAttribute("lastCheckoutOrder", order);
            response.sendRedirect(request.getContextPath() + "/checkout/success");
        } catch (CheckoutException_24162095 e) {
            request.setAttribute("error", e.getMessage());
            showForm(request, response);
        }
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = CartWebUtil_24162095.currentUserId(request);
        request.setAttribute("items", cartService.getItems(userId));
        request.setAttribute("total", cartService.getTotal(userId));
        request.setAttribute("totalQuantity", cartService.getTotalQuantity(userId));
        request.getRequestDispatcher("/views/web/checkout.jsp").forward(request, response);
    }
}
