package vn.hcmute.web24162095.dao.impl;

import vn.hcmute.web24162095.connection.DBConnection_24162095;
import vn.hcmute.web24162095.dao.UserDAO_24162095;
import vn.hcmute.web24162095.model.User_24162095;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl_24162095 implements UserDAO_24162095 {
    private static final String SELECT = "SELECT u.*, r.roleName, s.sellername FROM Users u JOIN UserRoles r ON u.roleId=r.roleId LEFT JOIN Seller s ON u.sellerId=s.sellerId ";

    @Override public Optional<User_24162095> getById(int id) { return one(SELECT + "WHERE u.userId=?", id); }
    @Override public Optional<User_24162095> getByUsername(String username) { return one(SELECT + "WHERE u.username=?", username); }
    @Override public Optional<User_24162095> getByEmail(String email) { return one(SELECT + "WHERE u.email=?", email); }

    private Optional<User_24162095> one(String sql, Object value) {
        try (Connection c = DBConnection_24162095.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setObject(1, value);
            try (ResultSet r = p.executeQuery()) { return r.next() ? Optional.of(map(r)) : Optional.empty(); }
        } catch (SQLException e) { throw new DataAccessException_24162095("Không thể truy vấn người dùng", e); }
    }

    @Override public int insert(User_24162095 u) {
        String sql = "INSERT INTO Users(username,email,fullname,password,images,phone,status,code,roleId,sellerId) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (Connection c = DBConnection_24162095.getConnection(); PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bindCommon(p, u, true);
            p.executeUpdate();
            try (ResultSet keys = p.getGeneratedKeys()) { return keys.next() ? keys.getInt(1) : 0; }
        } catch (SQLException e) { throw new DataAccessException_24162095("Không thể thêm người dùng", e); }
    }

    private void bindCommon(PreparedStatement p, User_24162095 u, boolean includePassword) throws SQLException {
        int i = 1;
        p.setString(i++, u.getUsername()); p.setString(i++, u.getEmail()); p.setString(i++, u.getFullname());
        if (includePassword) p.setString(i++, u.getPassword());
        p.setString(i++, u.getImages()); p.setString(i++, u.getPhone()); p.setBoolean(i++, u.isStatus()); p.setString(i++, u.getCode()); p.setInt(i++, u.getRoleId());
        if (u.getSellerId() == null) p.setNull(i, Types.INTEGER); else p.setInt(i, u.getSellerId());
    }

    @Override public boolean update(User_24162095 u, boolean updatePassword) {
        String sql = updatePassword
                ? "UPDATE Users SET username=?,email=?,fullname=?,password=?,images=?,phone=?,status=?,code=?,roleId=?,sellerId=? WHERE userId=?"
                : "UPDATE Users SET username=?,email=?,fullname=?,images=?,phone=?,status=?,code=?,roleId=?,sellerId=? WHERE userId=?";
        try (Connection c = DBConnection_24162095.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            bindCommon(p, u, updatePassword);
            p.setInt(updatePassword ? 11 : 10, u.getUserId());
            return p.executeUpdate() > 0;
        } catch (SQLException e) { throw new DataAccessException_24162095("Không thể cập nhật người dùng", e); }
    }

    @Override public boolean delete(int id) {
        try (Connection c = DBConnection_24162095.getConnection(); PreparedStatement p = c.prepareStatement("DELETE FROM Users WHERE userId=?")) {
            p.setInt(1, id); return p.executeUpdate() > 0;
        } catch (SQLIntegrityConstraintViolationException e) { return false; }
        catch (SQLException e) { throw new DataAccessException_24162095("Không thể xóa người dùng", e); }
    }

    @Override public List<User_24162095> findAll(int page, int pageSize) {
        List<User_24162095> users = new ArrayList<>();
        String sql = SELECT + "ORDER BY u.userId LIMIT ? OFFSET ?";
        try (Connection c = DBConnection_24162095.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, pageSize); p.setInt(2, (page - 1) * pageSize);
            try (ResultSet r = p.executeQuery()) { while (r.next()) users.add(map(r)); }
            return users;
        } catch (SQLException e) { throw new DataAccessException_24162095("Không thể phân trang người dùng", e); }
    }

    @Override public int count() {
        try (Connection c = DBConnection_24162095.getConnection(); PreparedStatement p = c.prepareStatement("SELECT COUNT(*) FROM Users"); ResultSet r = p.executeQuery()) {
            r.next(); return r.getInt(1);
        } catch (SQLException e) { throw new DataAccessException_24162095("Không thể đếm người dùng", e); }
    }

    @Override public boolean checkExistUsername(String username) { return getByUsername(username).isPresent(); }
    @Override public boolean checkExistEmail(String email) { return getByEmail(email).isPresent(); }

    private User_24162095 map(ResultSet r) throws SQLException {
        User_24162095 u = new User_24162095();
        u.setUserId(r.getInt("userId")); u.setUsername(r.getString("username")); u.setEmail(r.getString("email")); u.setFullname(r.getString("fullname"));
        u.setPassword(r.getString("password")); u.setImages(r.getString("images")); u.setPhone(r.getString("phone")); u.setStatus(r.getBoolean("status"));
        u.setCode(r.getString("code")); u.setRoleId(r.getInt("roleId")); u.setRoleName(r.getString("roleName"));
        int sellerId = r.getInt("sellerId"); u.setSellerId(r.wasNull() ? null : sellerId); u.setSellerName(r.getString("sellername"));
        return u;
    }
}
