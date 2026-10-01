package vn.hcmute.web24162095.dao.impl;

import vn.hcmute.web24162095.connection.DBConnection_24162095;
import vn.hcmute.web24162095.dao.SellerDAO_24162095;
import vn.hcmute.web24162095.model.Seller_24162095;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SellerDAOImpl_24162095 implements SellerDAO_24162095 {
    @Override public Optional<Seller_24162095> getById(int id){try(Connection c=DBConnection_24162095.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM Seller WHERE sellerId=?")){p.setInt(1,id);try(ResultSet r=p.executeQuery()){return r.next()?Optional.of(map(r)):Optional.empty();}}catch(SQLException e){throw new DataAccessException_24162095("Không thể tải cửa hàng",e);}}
    @Override public List<Seller_24162095> getAll(){List<Seller_24162095> out=new ArrayList<>();try(Connection c=DBConnection_24162095.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM Seller ORDER BY sellerId");ResultSet r=p.executeQuery()){while(r.next())out.add(map(r));return out;}catch(SQLException e){throw new DataAccessException_24162095("Không thể tải cửa hàng",e);}}
    private Seller_24162095 map(ResultSet r)throws SQLException{Seller_24162095 x=new Seller_24162095();x.setSellerId(r.getInt("sellerId"));x.setSellerName(r.getString("sellername"));x.setImages(r.getString("images"));x.setStatus(r.getBoolean("status"));return x;}
}
