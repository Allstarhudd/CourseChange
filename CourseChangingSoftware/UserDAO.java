package com.coursechange.dao;

import com.coursechange.model.User;
import com.coursechange.util.DBConnection;

import java.sql.*;

public class UserDAO {

    public User authenticate(String username, String passwordHash) {
        String sql = "SELECT * FROM Users WHERE username=? AND password_hash=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, passwordHash);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
