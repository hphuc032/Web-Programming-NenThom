package vn.hcmute.web24162095.controller.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.model.PendingRegistration_24162095;
import vn.hcmute.web24162095.service.UserService_24162095;
import vn.hcmute.web24162095.service.impl.UserServiceImpl_24162095;
import vn.hcmute.web24162095.util.Constant_24162095;
import java.io.IOException;

@WebServlet("/verify-otp")
public class VerifyOtpController_24162095 extends HttpServlet {
    private final UserService_24162095 service=new UserServiceImpl_24162095();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{if(req.getSession(false)==null||req.getSession(false).getAttribute(Constant_24162095.SESSION_PENDING_REGISTRATION)==null){resp.sendRedirect(req.getContextPath()+"/register");return;}req.getRequestDispatcher("/views/auth/verify-otp.jsp").forward(req,resp);}
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{HttpSession session=req.getSession(false);PendingRegistration_24162095 pending=session==null?null:(PendingRegistration_24162095)session.getAttribute(Constant_24162095.SESSION_PENDING_REGISTRATION);if(pending==null){resp.sendRedirect(req.getContextPath()+"/register");return;}if(pending.isExpired()){session.removeAttribute(Constant_24162095.SESSION_PENDING_REGISTRATION);req.setAttribute("error","OTP đã hết hạn. Vui lòng đăng ký lại.");req.getRequestDispatcher("/views/auth/verify-otp.jsp").forward(req,resp);return;}if(!pending.verify(req.getParameter("otp"))){req.setAttribute("error","OTP không đúng.");doGet(req,resp);return;}service.register(pending.getUser());session.removeAttribute(Constant_24162095.SESSION_PENDING_REGISTRATION);session.setAttribute("flash","Kích hoạt tài khoản thành công. Hãy đăng nhập.");resp.sendRedirect(req.getContextPath()+"/login");}
}
