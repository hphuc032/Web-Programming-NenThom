package vn.hcmute.web24162095.dao.impl;

import vn.hcmute.web24162095.connection.DBConnection_24162095;
import vn.hcmute.web24162095.dao.ProductDAO_24162095;
import vn.hcmute.web24162095.model.Product_24162095;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAOImpl_24162095 implements ProductDAO_24162095 {
    private static final String SELECT="SELECT p.*,c.categoryName,s.sellername FROM Product p JOIN Category c ON p.categoryId=c.categoryId JOIN Seller s ON p.sellerId=s.sellerId ";
    @Override public List<Product_24162095> findAllOrderedBySeller(){List<Product_24162095> out=new ArrayList<>();try(Connection c=DBConnection_24162095.getConnection();PreparedStatement p=c.prepareStatement(SELECT+"WHERE p.status=1 ORDER BY p.sellerId,p.productId");ResultSet r=p.executeQuery()){while(r.next())out.add(map(r));return out;}catch(SQLException e){throw new DataAccessException_24162095("Không thể tải sản phẩm",e);}}
    @Override public Optional<Product_24162095> getById(int id){try(Connection c=DBConnection_24162095.getConnection();PreparedStatement p=c.prepareStatement(SELECT+"WHERE p.productId=?")){p.setInt(1,id);try(ResultSet r=p.executeQuery()){return r.next()?Optional.of(map(r)):Optional.empty();}}catch(SQLException e){throw new DataAccessException_24162095("Không thể tải chi tiết sản phẩm",e);}}
    private Product_24162095 map(ResultSet r)throws SQLException{Product_24162095 x=new Product_24162095();x.setProductId(r.getInt("productId"));x.setProductName(r.getString("productName"));x.setProductCode(r.getString("productCode"));x.setCategoryId(r.getInt("categoryId"));x.setCategoryName(r.getString("categoryName"));x.setDescription(r.getString("description"));x.setPrice(r.getBigDecimal("price"));x.setAmount(r.getInt("amount"));x.setStock(r.getInt("stock"));x.setImages(r.getString("images"));x.setWishlist(r.getInt("wishlist"));x.setStatus(r.getBoolean("status"));Timestamp t=r.getTimestamp("createDate");if(t!=null)x.setCreateDate(t.toLocalDateTime());x.setSellerId(r.getInt("sellerId"));x.setSellerName(r.getString("sellername"));return x;}
}
