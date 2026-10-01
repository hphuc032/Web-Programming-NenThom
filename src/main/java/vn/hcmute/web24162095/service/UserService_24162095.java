package vn.hcmute.web24162095.service;

import vn.hcmute.web24162095.model.Page_24162095;
import vn.hcmute.web24162095.model.User_24162095;
import java.util.Optional;

public interface UserService_24162095 {
    Optional<User_24162095> login(String username,String password);
    int register(User_24162095 user);
    int create(User_24162095 user);
    boolean update(User_24162095 user,String newPassword);
    boolean delete(int id,int currentUserId);
    Optional<User_24162095> getById(int id);
    boolean usernameExists(String username);
    boolean emailExists(String email);
    Page_24162095<User_24162095> findPage(int page,int size);
}
