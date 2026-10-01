package vn.hcmute.web24162095.controller.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/seller/home")
public class SellerHomeController_24162095 extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{req.getRequestDispatcher("/views/web/seller-home.jsp").forward(req,resp);}
}
