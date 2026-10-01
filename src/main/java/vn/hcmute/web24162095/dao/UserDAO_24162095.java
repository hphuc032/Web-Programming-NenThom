package vn.hcmute.web24162095.dao;

import vn.hcmute.web24162095.model.User_24162095;
import java.util.List;
import java.util.Optional;

public interface UserDAO_24162095 {
    Optional<User_24162095> getById(int id);
    Optional<User_24162095> getByUsername(String username);
    Optional<User_24162095> getByEmail(String email);
    int insert(User_24162095 user);
    boolean update(User_24162095 user, boolean updatePassword);
    boolean delete(int id);
    List<User_24162095> findAll(int page, int pageSize);
    int count();
    boolean checkExistUsername(String username);
    boolean checkExistEmail(String email);
}
