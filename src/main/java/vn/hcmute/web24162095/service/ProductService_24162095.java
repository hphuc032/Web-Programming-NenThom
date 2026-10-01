package vn.hcmute.web24162095.service;

import vn.hcmute.web24162095.model.Product_24162095;
import vn.hcmute.web24162095.model.Seller_24162095;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService_24162095 {
    Map<Seller_24162095,List<Product_24162095>> getProductsGroupedBySeller();
    Optional<Product_24162095> getById(int id);
}
