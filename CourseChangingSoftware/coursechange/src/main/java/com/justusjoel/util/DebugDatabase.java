package com.justusjoel.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Debug utility to test database connection and user retrieval
 */
public class DebugDatabase {
    public static void main(String[] args) {
        try {
            System.out.println("═══════════════════════════════════════════════");
            System.out.println("TESTING DATABASE CONNECTION AND USERS");
            System.out.println("═══════════════════════════════════════════════\n");

            Connection conn = com.justusjoel.util.DBConnection.getConnection();
            
            if (conn == null) {
                System.out.println("❌ Failed to connect to database!");
                return;
            }
            
            System.out.println("✓ Connected to database\n");

            // Get all users
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
            
            System.out.println("Users in database:");
            System.out.println("─────────────────────────────────────────────────");
            
            int count = 0;
            while (rs.next()) {
                count++;
                System.out.println("User #" + count + ":");
                System.out.println("  user_id: " + rs.getInt("user_id"));
                System.out.println("  username: [" + rs.getString("username") + "]");
                System.out.println("  password_hash: [" + rs.getString("password_hash") + "]");
                System.out.println("  role: [" + rs.getString("role") + "]");
                System.out.println();
            }
            
            if (count == 0) {
                System.out.println("⚠️  No users found in database!");
            } else {
                System.out.println("─────────────────────────────────────────────────");
                System.out.println("\nTesting password hashes:");
                System.out.println("─────────────────────────────────────────────────");
                
                String studentHash = PasswordUtil.hashPassword("pass123");
                String adminHash = PasswordUtil.hashPassword("admin123");
                
                System.out.println("\nExpected hashes:");
                System.out.println("  student1/pass123: " + studentHash);
                System.out.println("  admin1/admin123:  " + adminHash);
            }
            
            conn.close();
            
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
