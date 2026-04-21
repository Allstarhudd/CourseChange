package com.justusjoel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Direct SQL Server connection test
 */
public class TestDirectConnection {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=CourseChangeDB;trustServerCertificate=true";
        String user = "course_user";
        String password = "StrongPassword123";
        
        try {
            System.out.println("Attempting to connect to SQL Server...");
            System.out.println("URL: " + url);
            System.out.println("User: " + user);
            System.out.println();
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(url, user, password);
            
            System.out.println("✓ Connected successfully!\n");
            
            // Query Users table
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TOP 10 * FROM Users");
            
            System.out.println("Users in database:");
            System.out.println("─────────────────────────────────────────────────");
            
            int count = 0;
            while (rs.next()) {
                count++;
                System.out.println("User " + count + ":");
                System.out.println("  user_id: " + rs.getInt("user_id"));
                System.out.println("  username: '" + rs.getString("username") + "'");
                System.out.println("  password_hash: '" + rs.getString("password_hash") + "'");
                System.out.println("  role: '" + rs.getString("role") + "'");
                System.out.println();
            }
            
            if (count == 0) {
                System.out.println("⚠️  NO USERS FOUND IN DATABASE!");
                System.out.println("The INSERT statement didn't work or users aren't in the database.");
            }
            
            conn.close();
            
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
