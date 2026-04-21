package com.justusjoel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.justusjoel.model.User;
import com.justusjoel.util.DBConnection;

public class UserDAO {

    public User authenticate(String username, String password) throws SQLException {
        String sql = "SELECT * FROM Users WHERE username=? AND password_hash=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);  // Use plain password directly
            
            System.out.println("\n=== UserDAO Debug ===");
            System.out.println("SQL: " + sql);
            System.out.println("Looking for: username='" + username + "', password='" + password + "'");

            ResultSet rs = ps.executeQuery();
            
            System.out.println("Query executed, checking results...");
            
            if (rs.next()) {
                System.out.println("✓ User found!");
                System.out.println("  user_id: " + rs.getInt("user_id"));
                System.out.println("  username: " + rs.getString("username"));
                System.out.println("  role: " + rs.getString("role"));
                
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("role")
                );
            } else {
                System.out.println("❌ No user found matching those credentials");
                
                // Try to see what users exist
                try (PreparedStatement ps2 = conn.prepareStatement("SELECT username, password_hash FROM Users")) {
                    ResultSet rs2 = ps2.executeQuery();
                    System.out.println("\nUsers in database:");
                    while (rs2.next()) {
                        System.out.println("  - " + rs2.getString("username") + " / " + rs2.getString("password_hash"));
                    }
                }
            }
        }
        return null;
    }
}
