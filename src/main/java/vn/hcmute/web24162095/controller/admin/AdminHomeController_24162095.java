package vn.hcmute.web24162095.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/home")
public class AdminHomeController_24162095 extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{req.getRequestDispatcher("/views/admin/home.jsp").forward(req,resp);}
}
