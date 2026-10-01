package vn.hcmute.web24162095.service;

import vn.hcmute.web24162095.model.Seller_24162095;
import java.util.List;
import java.util.Optional;

public interface SellerService_24162095 {
    Optional<Seller_24162095> getById(int id);
    List<Seller_24162095> getAll();
}
