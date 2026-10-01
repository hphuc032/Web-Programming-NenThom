package vn.hcmute.web24162095.service.impl;

import vn.hcmute.web24162095.dao.UserDAO_24162095;
import vn.hcmute.web24162095.dao.impl.UserDAOImpl_24162095;
import vn.hcmute.web24162095.model.Page_24162095;
import vn.hcmute.web24162095.model.User_24162095;
import vn.hcmute.web24162095.service.UserService_24162095;
import vn.hcmute.web24162095.util.PasswordUtil_24162095;
import java.util.Optional;

public class UserServiceImpl_24162095 implements UserService_24162095 {
    private final UserDAO_24162095 dao;
    public UserServiceImpl_24162095(){this(new UserDAOImpl_24162095());}
    public UserServiceImpl_24162095(UserDAO_24162095 dao){this.dao=dao;}
    @Override public Optional<User_24162095> login(String username,String password){return dao.getByUsername(username).filter(User_24162095::isStatus).filter(u->PasswordUtil_24162095.matches(password,u.getPassword()));}
    @Override public int register(User_24162095 user){user.setRoleId(2);user.setSellerId(null);user.setStatus(true);return create(user);}
    @Override public int create(User_24162095 user){validate(user,0);if(user.getPassword()==null||user.getPassword().length()<6)throw new IllegalArgumentException("Mật khẩu phải có ít nhất 6 ký tự.");user.setPassword(PasswordUtil_24162095.hash(user.getPassword()));return dao.insert(user);}
    @Override public boolean update(User_24162095 user,String newPassword){User_24162095 old=dao.getById(user.getUserId()).orElseThrow(()->new IllegalArgumentException("Không tìm thấy người dùng."));validate(user,user.getUserId());boolean change=newPassword!=null&&!newPassword.isBlank();user.setPassword(change?PasswordUtil_24162095.hash(newPassword):old.getPassword());return dao.update(user,change);}
    @Override public boolean delete(int id,int currentUserId){if(id==currentUserId)throw new IllegalArgumentException("Không thể xóa tài khoản đang đăng nhập.");return dao.delete(id);}
    @Override public Optional<User_24162095> getById(int id){return dao.getById(id);}
    @Override public boolean usernameExists(String username){return dao.checkExistUsername(username);}
    @Override public boolean emailExists(String email){return dao.checkExistEmail(email);}
    @Override public Page_24162095<User_24162095> findPage(int page,int size){int safePage=Math.max(1,page);int safeSize=Math.max(1,Math.min(size,50));return new Page_24162095<>(dao.findAll(safePage,safeSize),safePage,safeSize,dao.count());}
    private void validate(User_24162095 u,int self){if(u.getUsername()==null||u.getUsername().isBlank()||u.getEmail()==null||u.getEmail().isBlank()||u.getFullname()==null||u.getFullname().isBlank())throw new IllegalArgumentException("Username, email và họ tên là bắt buộc.");dao.getByUsername(u.getUsername()).filter(x->x.getUserId()!=self).ifPresent(x->{throw new IllegalArgumentException("Username đã tồn tại.");});dao.getByEmail(u.getEmail()).filter(x->x.getUserId()!=self).ifPresent(x->{throw new IllegalArgumentException("Email đã tồn tại.");});}
}
