package vn.hcmute.web24162095.controller.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.service.CartService_24162095;
import vn.hcmute.web24162095.service.impl.CartServiceImpl_24162095;
import vn.hcmute.web24162095.util.CartWebUtil_24162095;

import java.io.IOException;

@WebServlet("/cart/clear")
public class ClearCartController_24162095 extends HttpServlet {
    private final CartService_24162095 service = new CartServiceImpl_24162095();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.clearCart(CartWebUtil_24162095.currentUserId(request));
        CartWebUtil_24162095.flash(request, "success", "Đã xóa toàn bộ giỏ hàng.");
        response.sendRedirect(request.getContextPath() + "/cart");
    }
}
