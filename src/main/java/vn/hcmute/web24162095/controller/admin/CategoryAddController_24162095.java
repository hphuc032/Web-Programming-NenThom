package vn.hcmute.web24162095.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.model.Category_24162095;
import vn.hcmute.web24162095.service.CategoryService_24162095;
import vn.hcmute.web24162095.service.impl.CategoryServiceImpl_24162095;
import vn.hcmute.web24162095.util.RequestUtil_24162095;
import java.io.IOException;

@WebServlet("/admin/category/add")
public class CategoryAddController_24162095 extends HttpServlet {
    private final CategoryService_24162095 service=new CategoryServiceImpl_24162095();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{req.getRequestDispatcher("/views/admin/categories/form.jsp").forward(req,resp);}
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{Category_24162095 x=read(req);try{service.create(x);resp.sendRedirect(req.getContextPath()+"/admin/categories?message=Đã thêm danh mục");}catch(IllegalArgumentException e){req.setAttribute("error",e.getMessage());req.setAttribute("category",x);doGet(req,resp);}}
    private Category_24162095 read(HttpServletRequest r){Category_24162095 x=new Category_24162095();x.setCategoryName(RequestUtil_24162095.trim(r.getParameter("categoryName")));x.setImages(RequestUtil_24162095.trim(r.getParameter("images")));x.setStatus("true".equals(r.getParameter("status")));return x;}
}
