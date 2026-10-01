package vn.hcmute.web24162095.controller.admin;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.service.CategoryService_24162095;
import vn.hcmute.web24162095.service.impl.CategoryServiceImpl_24162095;
import vn.hcmute.web24162095.util.RequestUtil_24162095;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/admin/category/delete")
public class CategoryDeleteController_24162095 extends HttpServlet {
    private final CategoryService_24162095 service=new CategoryServiceImpl_24162095();
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws IOException{boolean ok=service.delete(RequestUtil_24162095.intParam(req,"id",-1));String message=ok?"Đã xóa danh mục":"Không thể xóa danh mục đang có sản phẩm";resp.sendRedirect(req.getContextPath()+"/admin/categories?message="+URLEncoder.encode(message,StandardCharsets.UTF_8));}
}
