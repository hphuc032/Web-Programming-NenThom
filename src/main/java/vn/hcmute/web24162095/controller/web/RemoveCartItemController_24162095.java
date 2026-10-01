package vn.hcmute.web24162095.controller.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.service.CartException_24162095;
import vn.hcmute.web24162095.service.CartService_24162095;
import vn.hcmute.web24162095.service.impl.CartServiceImpl_24162095;
import vn.hcmute.web24162095.util.CartWebUtil_24162095;
import vn.hcmute.web24162095.util.RequestUtil_24162095;

import java.io.IOException;

@WebServlet("/cart/remove")
public class RemoveCartItemController_24162095 extends HttpServlet {
    private final CartService_24162095 service = new CartServiceImpl_24162095();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = RequestUtil_24162095.intParam(request, "productId", -1);
        try {
            service.removeProduct(CartWebUtil_24162095.currentUserId(request), productId);
            CartWebUtil_24162095.flash(request, "success", "Đã xóa sản phẩm khỏi giỏ hàng.");
        } catch (CartException_24162095 e) {
            CartWebUtil_24162095.flash(request, "danger", e.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/cart");
    }
}
