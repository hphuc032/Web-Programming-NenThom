package vn.hcmute.web24162095.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hcmute.web24162095.model.User_24162095;

public final class CartWebUtil_24162095 {
    private CartWebUtil_24162095() { }

    public static int currentUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object account = session == null ? null : session.getAttribute(Constant_24162095.SESSION_ACCOUNT);
        return account instanceof User_24162095 user ? user.getUserId() : -1;
    }

    public static void flash(HttpServletRequest request, String type, String message) {
        request.getSession().setAttribute("cartFlashType", type);
        request.getSession().setAttribute("cartFlashMessage", message);
    }

    public static void consumeFlash(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return;
        Object type = session.getAttribute("cartFlashType");
        Object message = session.getAttribute("cartFlashMessage");
        if (message != null) {
            request.setAttribute("cartFlashType", type);
            request.setAttribute("cartFlashMessage", message);
            session.removeAttribute("cartFlashType");
            session.removeAttribute("cartFlashMessage");
        }
    }
}
