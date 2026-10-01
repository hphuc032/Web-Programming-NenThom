package vn.hcmute.web24162095.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.model.Category_24162095;
import vn.hcmute.web24162095.service.CategoryService_24162095;
import vn.hcmute.web24162095.service.impl.CategoryServiceImpl_24162095;
import vn.hcmute.web24162095.util.RequestUtil_24162095;
import java.io.IOException;

@WebServlet("/admin/category/edit")
public class CategoryEditController_24162095 extends HttpServlet {
    private final CategoryService_24162095 service=new CategoryServiceImpl_24162095();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{int id=RequestUtil_24162095.intParam(req,"id",-1);if(req.getAttribute("category")==null){Category_24162095 x=service.getById(id).orElse(null);if(x==null){resp.sendError(404);return;}req.setAttribute("category",x);}req.getRequestDispatcher("/views/admin/categories/form.jsp").forward(req,resp);}
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{Category_24162095 x=new Category_24162095();x.setCategoryId(RequestUtil_24162095.intParam(req,"id",-1));x.setCategoryName(RequestUtil_24162095.trim(req.getParameter("categoryName")));x.setImages(RequestUtil_24162095.trim(req.getParameter("images")));x.setStatus("true".equals(req.getParameter("status")));try{service.update(x);resp.sendRedirect(req.getContextPath()+"/admin/categories?message=Đã cập nhật danh mục");}catch(IllegalArgumentException e){req.setAttribute("error",e.getMessage());req.setAttribute("category",x);doGet(req,resp);}}
}
