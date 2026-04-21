package com.justusjoel.util;

/**
 * Inserts demo users directly using DBConnection
 */
public class InsertDemoUsers {
    public static void main(String[] args) {
        try {
            System.out.println("Attempting to insert demo users...\n");
            
            String student_password = "pass123";
            String admin_password = "admin123";
            
            String student_hash = PasswordUtil.hashPassword(student_password);
            String admin_hash = PasswordUtil.hashPassword(admin_password);
            
            System.out.println("Student hash: " + student_hash);
            System.out.println("Admin hash: " + admin_hash + "\n");
            
            java.sql.Connection conn = DBConnection.getConnection();
            
            if (conn == null) {
                System.out.println("❌ Failed to connect to database!");
                return;
            }
            
            System.out.println("✓ Connected to database\n");
            
            String insertSQL = "INSERT INTO Users (username, password_hash, role) VALUES (?, ?, ?)";
            java.sql.PreparedStatement ps = conn.prepareStatement(insertSQL);
            
            // Insert student
            ps.setString(1, "student1");
            ps.setString(2, student_hash);
            ps.setString(3, "STUDENT");
            int rows1 = ps.executeUpdate();
            System.out.println("Inserted student: " + rows1 + " row(s)");
            
            // Insert admin
            ps.setString(1, "admin1");
            ps.setString(2, admin_hash);
            ps.setString(3, "ADMIN");
            int rows2 = ps.executeUpdate();
            System.out.println("Inserted admin: " + rows2 + " row(s)");
            
            ps.close();
            conn.close();
            
            System.out.println("\n✓ Demo users inserted successfully!");
            
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
