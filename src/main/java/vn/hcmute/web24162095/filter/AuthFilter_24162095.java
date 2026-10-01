package vn.hcmute.web24162095.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.util.Constant_24162095;
import java.io.IOException;

public class AuthFilter_24162095 implements Filter {
    @Override public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException,ServletException{HttpServletRequest req=(HttpServletRequest)request;HttpServletResponse resp=(HttpServletResponse)response;HttpSession s=req.getSession(false);if(s==null||s.getAttribute(Constant_24162095.SESSION_ACCOUNT)==null){resp.sendRedirect(req.getContextPath()+"/login");return;}chain.doFilter(request,response);}
}
