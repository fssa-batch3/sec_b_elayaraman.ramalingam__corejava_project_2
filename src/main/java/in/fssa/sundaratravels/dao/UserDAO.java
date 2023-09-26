package in.fssa.sundaratravels.dao;

import in.fssa.sundaratravels.exception.PersistenceException;
import in.fssa.sundaratravels.model.User;
import in.fssa.sundaratravels.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static final String INSERT_USER_SQL = "INSERT INTO users (user_name, phone_number, password,dob) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_USER_DETAILS_SQL = "UPDATE users SET user_name = ?, phone_number = ?, password = ?, dob = ? WHERE user_id = ?";
    private static final String UPDATE_USER_STATUS_SQL = "UPDATE users SET is_active = ? WHERE user_id = ?";
    private static final String GET_USER_DETAILS_SQL = "SELECT user_id, user_name, phone_number, password, is_active, dob FROM users WHERE phone_number = ? AND is_active = 1";

    private static final String GET_USER_DETAILS_BY_USERID_SQL = "SELECT user_id, user_name, phone_number, password, is_active, dob FROM users WHERE user_id = ? AND is_active = 1";

    public void createUser(User user) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        try { conn = ConnectionUtil.getConnection();
              ps = conn.prepareStatement(INSERT_USER_SQL);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPhone()+"");
            ps.setString(3, user.getPassword());
            ps.setDate(4, user.getDob());
            ps.executeUpdate();
        } catch (SQLException e) {
        	if(e.getMessage().contains("duplicate")) {
        		throw new PersistenceException("User already exists");
        	}else {
            throw new PersistenceException(e.getMessage());
            }
        }finally {
            ConnectionUtil.close(conn,ps);
        }
    }

    public User getUserByPhoneNumber(long phonenumber) throws PersistenceException{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
             conn = ConnectionUtil.getConnection();
             ps = conn.prepareStatement(GET_USER_DETAILS_SQL);
             ps.setLong(1,phonenumber);
             rs = ps.executeQuery();
             
             if(rs.next())
             user = extractUserResutSet(rs);
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }finally {
        	ConnectionUtil.close(conn, ps,rs);
        }
        return user;
    }

    public User getUserByUserId(int id) throws PersistenceException{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(GET_USER_DETAILS_BY_USERID_SQL);
            ps.setLong(1,id);
            rs = ps.executeQuery();

            if(rs.next())
                user = extractUserResutSet(rs);
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }finally {
            ConnectionUtil.close(conn, ps,rs);
        }
        return user;
    }

    public void updateUserDetails(User user) throws PersistenceException {
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_USER_DETAILS_SQL)) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPhone()+"");
            ps.setString(3, user.getPassword());
            ps.setDate(4, user.getDob());
            ps.setInt(5, user.getUserId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated == 0) {
                throw new PersistenceException("User not found with ID: " + user.getUserId());
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void changeUserStatus(int userId, boolean isActive) throws PersistenceException {
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_USER_STATUS_SQL)) {
            ps.setBoolean(1, isActive);
            ps.setInt(2, userId);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated == 0) {
                throw new PersistenceException("User not found with ID: " + userId);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    private User extractUserResutSet(ResultSet rs)throws SQLException{
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUserName(rs.getString("user_name"));
        user.setDob(rs.getDate("dob"));
        user.setPhone(rs.getLong("phone_number"));
        user.setPassword(rs.getString("password"));
        user.setActive(rs.getBoolean("is_active"));
        return user;
    }
}
