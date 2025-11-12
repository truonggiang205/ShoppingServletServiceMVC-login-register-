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
        try (Connection conn = new MySqlConnect().getConnection();
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
}