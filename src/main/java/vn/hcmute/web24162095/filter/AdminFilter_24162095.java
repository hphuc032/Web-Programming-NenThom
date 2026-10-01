package vn.hcmute.web24162095.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.model.User_24162095;
import vn.hcmute.web24162095.util.Constant_24162095;
import java.io.IOException;

public class AdminFilter_24162095 implements Filter {
    @Override public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException,ServletException{HttpServletRequest req=(HttpServletRequest)request;HttpServletResponse resp=(HttpServletResponse)response;HttpSession s=req.getSession(false);User_24162095 u=s==null?null:(User_24162095)s.getAttribute(Constant_24162095.SESSION_ACCOUNT);if(u==null||!Constant_24162095.ROLE_ADMIN.equals(u.getRoleName())){resp.sendError(HttpServletResponse.SC_FORBIDDEN,"Chỉ ADMIN được truy cập.");return;}chain.doFilter(request,response);}
}
