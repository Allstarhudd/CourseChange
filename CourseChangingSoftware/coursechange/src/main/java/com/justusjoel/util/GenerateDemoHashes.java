package com.justusjoel.util;

/**
 * Utility to generate password hashes for demo users
 * Run this to get hashes for: student1/pass123 and admin1/admin123
 */
public class GenerateDemoHashes {
    public static void main(String[] args) {
        String student_password = "pass123";
        String admin_password = "admin123";
        
        String student_hash = PasswordUtil.hashPassword(student_password);
        String admin_hash = PasswordUtil.hashPassword(admin_password);
        
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("DEMO USER PASSWORD HASHES");
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("\nStudent User:");
        System.out.println("  Username: student1");
        System.out.println("  Password: pass123");
        System.out.println("  Hash: " + student_hash);
        
        System.out.println("\nAdmin User:");
        System.out.println("  Username: admin1");
        System.out.println("  Password: admin123");
        System.out.println("  Hash: " + admin_hash);
        
        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("SQL INSERT STATEMENTS:");
        System.out.println("═══════════════════════════════════════════════\n");
        
        System.out.println("INSERT INTO Users (username, password_hash, role) VALUES");
        System.out.println("('student1', '" + student_hash + "', 'STUDENT'),");
        System.out.println("('admin1', '" + admin_hash + "', 'ADMIN');");
        
        System.out.println("\n═══════════════════════════════════════════════");
    }
}
