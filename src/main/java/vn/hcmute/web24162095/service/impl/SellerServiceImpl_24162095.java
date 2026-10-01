package vn.hcmute.web24162095.service.impl;

import vn.hcmute.web24162095.dao.SellerDAO_24162095;
import vn.hcmute.web24162095.dao.impl.SellerDAOImpl_24162095;
import vn.hcmute.web24162095.model.Seller_24162095;
import vn.hcmute.web24162095.service.SellerService_24162095;
import java.util.List;
import java.util.Optional;

public class SellerServiceImpl_24162095 implements SellerService_24162095 {
    private final SellerDAO_24162095 dao;
    public SellerServiceImpl_24162095(){this(new SellerDAOImpl_24162095());}
    public SellerServiceImpl_24162095(SellerDAO_24162095 dao){this.dao=dao;}
    @Override public Optional<Seller_24162095> getById(int id){return dao.getById(id);}
    @Override public List<Seller_24162095> getAll(){return dao.getAll();}
}
