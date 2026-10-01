package vn.hcmute.web24162095.controller.auth;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController_24162095 extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws IOException{HttpSession s=req.getSession(false);if(s!=null)s.invalidate();resp.sendRedirect(req.getContextPath()+"/login");}
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws IOException{doGet(req,resp);}
}
