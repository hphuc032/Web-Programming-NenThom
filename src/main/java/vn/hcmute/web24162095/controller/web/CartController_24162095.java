package vn.hcmute.web24162095.controller.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.service.CartService_24162095;
import vn.hcmute.web24162095.service.impl.CartServiceImpl_24162095;
import vn.hcmute.web24162095.util.CartWebUtil_24162095;

import java.io.IOException;

@WebServlet("/cart")
public class CartController_24162095 extends HttpServlet {
    private final CartService_24162095 service = new CartServiceImpl_24162095();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = CartWebUtil_24162095.currentUserId(request);
        request.setAttribute("cart", service.getOrCreateCart(userId));
        request.setAttribute("items", service.getItems(userId));
        request.setAttribute("total", service.getTotal(userId));
        request.setAttribute("totalQuantity", service.getTotalQuantity(userId));
        CartWebUtil_24162095.consumeFlash(request);
        request.getRequestDispatcher("/views/web/cart.jsp").forward(request, response);
    }
}
