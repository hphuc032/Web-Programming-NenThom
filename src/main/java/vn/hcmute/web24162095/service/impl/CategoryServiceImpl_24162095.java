package vn.hcmute.web24162095.service.impl;

import vn.hcmute.web24162095.dao.CategoryDAO_24162095;
import vn.hcmute.web24162095.dao.impl.CategoryDAOImpl_24162095;
import vn.hcmute.web24162095.model.Category_24162095;
import vn.hcmute.web24162095.model.Page_24162095;
import vn.hcmute.web24162095.service.CategoryService_24162095;
import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl_24162095 implements CategoryService_24162095 {
    private final CategoryDAO_24162095 dao;
    public CategoryServiceImpl_24162095(){this(new CategoryDAOImpl_24162095());}
    public CategoryServiceImpl_24162095(CategoryDAO_24162095 dao){this.dao=dao;}
    @Override public int create(Category_24162095 x){validate(x);return dao.insert(x);}
    @Override public boolean update(Category_24162095 x){validate(x);return dao.update(x);}
    @Override public boolean delete(int id){return dao.delete(id);}
    @Override public Optional<Category_24162095> getById(int id){return dao.getById(id);}
    @Override public List<Category_24162095> getAll(){return dao.getAll();}
    @Override public Page_24162095<Category_24162095> findPage(int page,int size){int p=Math.max(1,page),s=Math.max(1,Math.min(size,50));return new Page_24162095<>(dao.findAll(p,s),p,s,dao.count());}
    private void validate(Category_24162095 x){if(x.getCategoryName()==null||x.getCategoryName().isBlank())throw new IllegalArgumentException("Tên danh mục là bắt buộc.");}
}
