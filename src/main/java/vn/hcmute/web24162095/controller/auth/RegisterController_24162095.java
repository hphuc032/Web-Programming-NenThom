package vn.hcmute.web24162095.controller.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.model.PendingRegistration_24162095;
import vn.hcmute.web24162095.model.User_24162095;
import vn.hcmute.web24162095.service.UserService_24162095;
import vn.hcmute.web24162095.service.impl.UserServiceImpl_24162095;
import vn.hcmute.web24162095.util.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController_24162095 extends HttpServlet {
    private final UserService_24162095 service=new UserServiceImpl_24162095();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{req.getRequestDispatcher("/views/auth/register.jsp").forward(req,resp);}
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{String username=RequestUtil_24162095.trim(req.getParameter("username"));String email=RequestUtil_24162095.trim(req.getParameter("email"));String fullname=RequestUtil_24162095.trim(req.getParameter("fullname"));String password=req.getParameter("password");String confirm=req.getParameter("confirmPassword");String phone=RequestUtil_24162095.trim(req.getParameter("phone"));try{if(username==null||username.isBlank()||email==null||email.isBlank()||fullname==null||fullname.isBlank())throw new IllegalArgumentException("Vui lòng nhập đủ thông tin bắt buộc.");if(password==null||password.length()<6)throw new IllegalArgumentException("Mật khẩu phải có ít nhất 6 ký tự.");if(!password.equals(confirm))throw new IllegalArgumentException("Xác nhận mật khẩu không khớp.");if(service.usernameExists(username))throw new IllegalArgumentException("Username đã tồn tại.");if(service.emailExists(email))throw new IllegalArgumentException("Email đã tồn tại.");User_24162095 user=new User_24162095();user.setUsername(username);user.setEmail(email);user.setFullname(fullname);user.setPassword(password);user.setPhone(phone);user.setImages("https://placehold.co/160x160?text=User");String otp=OtpUtil_24162095.generate();MailUtil_24162095.sendOtp(email,otp);req.getSession(true).setAttribute(Constant_24162095.SESSION_PENDING_REGISTRATION,new PendingRegistration_24162095(user,otp,System.currentTimeMillis()+Constant_24162095.OTP_TTL_MILLIS));resp.sendRedirect(req.getContextPath()+"/verify-otp");}catch(Exception e){req.setAttribute("error",e.getMessage());doGet(req,resp);}}
}
