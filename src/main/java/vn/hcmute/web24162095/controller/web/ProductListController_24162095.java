package vn.hcmute.web24162095.controller.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.service.ProductService_24162095;
import vn.hcmute.web24162095.service.impl.ProductServiceImpl_24162095;
import java.io.IOException;

@WebServlet("/products")
public class ProductListController_24162095 extends HttpServlet {
    private final ProductService_24162095 service=new ProductServiceImpl_24162095();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{req.setAttribute("groups",service.getProductsGroupedBySeller());req.getRequestDispatcher("/views/web/products.jsp").forward(req,resp);}
}
