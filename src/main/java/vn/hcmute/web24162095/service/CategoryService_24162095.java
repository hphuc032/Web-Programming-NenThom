package vn.hcmute.web24162095.service;

import vn.hcmute.web24162095.model.Category_24162095;
import vn.hcmute.web24162095.model.Page_24162095;
import java.util.List;
import java.util.Optional;

public interface CategoryService_24162095 {
    int create(Category_24162095 category);
    boolean update(Category_24162095 category);
    boolean delete(int id);
    Optional<Category_24162095> getById(int id);
    List<Category_24162095> getAll();
    Page_24162095<Category_24162095> findPage(int page,int size);
}
