package vn.hcmute.web24162095.controller.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.model.User_24162095;
import vn.hcmute.web24162095.service.UserService_24162095;
import vn.hcmute.web24162095.service.impl.UserServiceImpl_24162095;
import vn.hcmute.web24162095.util.Constant_24162095;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginController_24162095 extends HttpServlet {
    private final UserService_24162095 service=new UserServiceImpl_24162095();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{req.getRequestDispatcher("/views/auth/login.jsp").forward(req,resp);}
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{Optional<User_24162095> found=service.login(req.getParameter("username"),req.getParameter("password"));if(found.isEmpty()){req.setAttribute("error","Sai tên đăng nhập, mật khẩu hoặc tài khoản đã khóa.");doGet(req,resp);return;}HttpSession old=req.getSession(false);if(old!=null)old.invalidate();HttpSession session=req.getSession(true);User_24162095 user=found.get();session.setAttribute(Constant_24162095.SESSION_ACCOUNT,user);session.setAttribute("role",user.getRoleName());if(Constant_24162095.ROLE_ADMIN.equals(user.getRoleName()))resp.sendRedirect(req.getContextPath()+"/admin/home");else if(user.getSellerId()!=null)resp.sendRedirect(req.getContextPath()+"/seller/home");else resp.sendRedirect(req.getContextPath()+"/home");}
}
