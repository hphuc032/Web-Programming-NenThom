package vn.hcmute.web24162095.dao.impl;

import vn.hcmute.web24162095.connection.DBConnection_24162095;
import vn.hcmute.web24162095.dao.CategoryDAO_24162095;
import vn.hcmute.web24162095.model.Category_24162095;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDAOImpl_24162095 implements CategoryDAO_24162095 {
    @Override public int insert(Category_24162095 x) {
        try (Connection c=DBConnection_24162095.getConnection(); PreparedStatement p=c.prepareStatement("INSERT INTO Category(categoryName,images,status) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            p.setString(1,x.getCategoryName()); p.setString(2,x.getImages()); p.setBoolean(3,x.isStatus()); p.executeUpdate();
            try(ResultSet r=p.getGeneratedKeys()){ return r.next()?r.getInt(1):0; }
        } catch(SQLException e){ throw new DataAccessException_24162095("Không thể thêm danh mục",e); }
    }
    @Override public boolean update(Category_24162095 x) {
        try(Connection c=DBConnection_24162095.getConnection(); PreparedStatement p=c.prepareStatement("UPDATE Category SET categoryName=?,images=?,status=? WHERE categoryId=?")){
            p.setString(1,x.getCategoryName());p.setString(2,x.getImages());p.setBoolean(3,x.isStatus());p.setInt(4,x.getCategoryId());return p.executeUpdate()>0;
        }catch(SQLException e){throw new DataAccessException_24162095("Không thể cập nhật danh mục",e);}
    }
    @Override public boolean delete(int id) {
        try(Connection c=DBConnection_24162095.getConnection(); PreparedStatement check=c.prepareStatement("SELECT COUNT(*) FROM Product WHERE categoryId=?")){
            check.setInt(1,id);try(ResultSet r=check.executeQuery()){r.next();if(r.getInt(1)>0)return false;}
            try(PreparedStatement p=c.prepareStatement("DELETE FROM Category WHERE categoryId=?")){p.setInt(1,id);return p.executeUpdate()>0;}
        }catch(SQLException e){throw new DataAccessException_24162095("Không thể xóa danh mục",e);}
    }
    @Override public Optional<Category_24162095> getById(int id){
        try(Connection c=DBConnection_24162095.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM Category WHERE categoryId=?")){p.setInt(1,id);try(ResultSet r=p.executeQuery()){return r.next()?Optional.of(map(r)):Optional.empty();}}
        catch(SQLException e){throw new DataAccessException_24162095("Không thể truy vấn danh mục",e);}
    }
    @Override public List<Category_24162095> getAll(){return list("SELECT * FROM Category ORDER BY categoryName",0,0);}
    @Override public List<Category_24162095> findAll(int page,int size){return list("SELECT * FROM Category ORDER BY categoryId LIMIT ? OFFSET ?",size,(page-1)*size);}
    private List<Category_24162095> list(String sql,int size,int offset){List<Category_24162095> result=new ArrayList<>();try(Connection c=DBConnection_24162095.getConnection();PreparedStatement p=c.prepareStatement(sql)){if(size>0){p.setInt(1,size);p.setInt(2,offset);}try(ResultSet r=p.executeQuery()){while(r.next())result.add(map(r));}return result;}catch(SQLException e){throw new DataAccessException_24162095("Không thể tải danh mục",e);}}
    @Override public int count(){try(Connection c=DBConnection_24162095.getConnection();PreparedStatement p=c.prepareStatement("SELECT COUNT(*) FROM Category");ResultSet r=p.executeQuery()){r.next();return r.getInt(1);}catch(SQLException e){throw new DataAccessException_24162095("Không thể đếm danh mục",e);}}
    private Category_24162095 map(ResultSet r)throws SQLException{Category_24162095 x=new Category_24162095();x.setCategoryId(r.getInt("categoryId"));x.setCategoryName(r.getString("categoryName"));x.setImages(r.getString("images"));x.setStatus(r.getBoolean("status"));return x;}
}
