package vn.hcmute.web24162095.dao;

import vn.hcmute.web24162095.model.Product_24162095;
import java.util.List;
import java.util.Optional;

public interface ProductDAO_24162095 {
    List<Product_24162095> findAllOrderedBySeller();
    Optional<Product_24162095> getById(int id);
}
