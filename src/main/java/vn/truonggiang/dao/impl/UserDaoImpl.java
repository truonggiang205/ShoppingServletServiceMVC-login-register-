package vn.truonggiang.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vn.truonggiang.config.MySqlConnect;
import vn.truonggiang.model.User;
import vn.truonggiang.dao.UserDao; 

public class UserDaoImpl implements UserDao {

    @Override
    public User get(String username) {
        String sql = "SELECT * FROM `User` WHERE username = ? ";
        try (Connection conn = MySqlConnect.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, username.trim());
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    
                    return user; 
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        
        return null;
    }
    
    @Override
    public void insert(User user) {
        String sql = "INSERT INTO `User`(username, password) VALUES (?, ?)"; 
        
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = MySqlConnect.getConnection();
            
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            
            ps.executeUpdate(); 

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}