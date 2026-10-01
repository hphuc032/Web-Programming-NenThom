package vn.hcmute.web24162095.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.model.User_24162095;
import vn.hcmute.web24162095.util.Constant_24162095;

import java.io.IOException;

public class UserFeatureFilter_24162095 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        Object account = session == null ? null : session.getAttribute(Constant_24162095.SESSION_ACCOUNT);
        if (!(account instanceof User_24162095 user)) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        if (!"USER".equals(user.getRoleName())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        chain.doFilter(request, response);
    }
}
