package vn.hcmute.web24162095.dao;

import vn.hcmute.web24162095.model.Category_24162095;
import java.util.List;
import java.util.Optional;

public interface CategoryDAO_24162095 {
    int insert(Category_24162095 category);
    boolean update(Category_24162095 category);
    boolean delete(int id);
    Optional<Category_24162095> getById(int id);
    List<Category_24162095> getAll();
    List<Category_24162095> findAll(int page, int pageSize);
    int count();
}
