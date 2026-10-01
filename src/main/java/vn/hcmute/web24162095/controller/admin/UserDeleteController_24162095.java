package vn.hcmute.web24162095.controller.admin;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.web24162095.model.User_24162095;
import vn.hcmute.web24162095.service.UserService_24162095;
import vn.hcmute.web24162095.service.impl.UserServiceImpl_24162095;
import vn.hcmute.web24162095.util.Constant_24162095;
import vn.hcmute.web24162095.util.RequestUtil_24162095;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/admin/user/delete")
public class UserDeleteController_24162095 extends HttpServlet {
    private final UserService_24162095 service=new UserServiceImpl_24162095();
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws IOException{User_24162095 current=(User_24162095)req.getSession().getAttribute(Constant_24162095.SESSION_ACCOUNT);String message;try{boolean ok=service.delete(RequestUtil_24162095.intParam(req,"id",-1),current.getUserId());message=ok?"Đã xóa người dùng":"Không thể xóa vì còn dữ liệu liên quan";}catch(IllegalArgumentException e){message=e.getMessage();}resp.sendRedirect(req.getContextPath()+"/admin/users?message="+URLEncoder.encode(message,StandardCharsets.UTF_8));}
}
