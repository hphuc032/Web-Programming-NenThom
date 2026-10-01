package vn.hcmute.web24162095.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.model.User_24162095;
import vn.hcmute.web24162095.service.*;
import vn.hcmute.web24162095.service.impl.*;
import vn.hcmute.web24162095.util.RequestUtil_24162095;
import java.io.IOException;

@WebServlet("/admin/user/add")
public class UserAddController_24162095 extends HttpServlet {
    private final UserService_24162095 users=new UserServiceImpl_24162095();private final SellerService_24162095 sellers=new SellerServiceImpl_24162095();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{req.setAttribute("sellers",sellers.getAll());req.getRequestDispatcher("/views/admin/users/form.jsp").forward(req,resp);}
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{User_24162095 u=read(req);try{users.create(u);resp.sendRedirect(req.getContextPath()+"/admin/users?message=Đã thêm người dùng");}catch(IllegalArgumentException e){req.setAttribute("error",e.getMessage());req.setAttribute("user",u);doGet(req,resp);}}
    private User_24162095 read(HttpServletRequest r){User_24162095 u=new User_24162095();u.setUsername(RequestUtil_24162095.trim(r.getParameter("username")));u.setEmail(RequestUtil_24162095.trim(r.getParameter("email")));u.setFullname(RequestUtil_24162095.trim(r.getParameter("fullname")));u.setPassword(r.getParameter("password"));u.setPhone(RequestUtil_24162095.trim(r.getParameter("phone")));u.setImages(RequestUtil_24162095.trim(r.getParameter("images")));u.setRoleId(RequestUtil_24162095.intParam(r,"roleId",2));u.setSellerId(RequestUtil_24162095.nullableInt(r.getParameter("sellerId")));u.setStatus("true".equals(r.getParameter("status")));return u;}
}
