package vn.hcmute.web24162095.service.impl;

import vn.hcmute.web24162095.dao.ProductDAO_24162095;
import vn.hcmute.web24162095.dao.impl.ProductDAOImpl_24162095;
import vn.hcmute.web24162095.model.Product_24162095;
import vn.hcmute.web24162095.model.Seller_24162095;
import vn.hcmute.web24162095.service.ProductService_24162095;
import vn.hcmute.web24162095.service.SellerService_24162095;
import java.util.*;

public class ProductServiceImpl_24162095 implements ProductService_24162095 {
    private final ProductDAO_24162095 productDAO;
    private final SellerService_24162095 sellerService;
    public ProductServiceImpl_24162095(){this(new ProductDAOImpl_24162095(),new SellerServiceImpl_24162095());}
    public ProductServiceImpl_24162095(ProductDAO_24162095 productDAO,SellerService_24162095 sellerService){this.productDAO=productDAO;this.sellerService=sellerService;}
    @Override public Map<Seller_24162095,List<Product_24162095>> getProductsGroupedBySeller(){Map<Integer,Seller_24162095> sellers=new HashMap<>();for(Seller_24162095 s:sellerService.getAll())sellers.put(s.getSellerId(),s);Map<Seller_24162095,List<Product_24162095>> grouped=new LinkedHashMap<>();for(Product_24162095 p:productDAO.findAllOrderedBySeller()){Seller_24162095 s=sellers.get(p.getSellerId());if(s!=null)grouped.computeIfAbsent(s,k->new ArrayList<>()).add(p);}return grouped;}
    @Override public Optional<Product_24162095> getById(int id){return productDAO.getById(id);}
}
