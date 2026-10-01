package vn.hcmute.web24162095.controller.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.service.ProductService_24162095;
import vn.hcmute.web24162095.service.impl.ProductServiceImpl_24162095;
import vn.hcmute.web24162095.util.RequestUtil_24162095;
import java.io.IOException;

@WebServlet("/product/detail")
public class ProductDetailController_24162095 extends HttpServlet {
    private final ProductService_24162095 service=new ProductServiceImpl_24162095();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{int id=RequestUtil_24162095.intParam(req,"id",-1);service.getById(id).ifPresentOrElse(p->{try{req.setAttribute("product",p);req.getRequestDispatcher("/views/web/product-detail.jsp").forward(req,resp);}catch(Exception e){throw new RuntimeException(e);}},()->{try{resp.sendError(HttpServletResponse.SC_NOT_FOUND,"Không tìm thấy sản phẩm");}catch(IOException e){throw new RuntimeException(e);}});}
}
