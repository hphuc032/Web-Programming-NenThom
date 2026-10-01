package vn.hcmute.web24162095.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.service.CategoryService_24162095;
import vn.hcmute.web24162095.service.impl.CategoryServiceImpl_24162095;
import vn.hcmute.web24162095.util.Constant_24162095;
import vn.hcmute.web24162095.util.RequestUtil_24162095;
import java.io.IOException;

@WebServlet("/admin/categories")
public class CategoryListController_24162095 extends HttpServlet {
    private final CategoryService_24162095 service=new CategoryServiceImpl_24162095();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{int page=RequestUtil_24162095.intParam(req,"page",1),size=RequestUtil_24162095.intParam(req,"size",Constant_24162095.DEFAULT_PAGE_SIZE);req.setAttribute("pageData",service.findPage(page,size));req.getRequestDispatcher("/views/admin/categories/list.jsp").forward(req,resp);}
}
