package com.justusjoel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.justusjoel.model.Student;
import com.justusjoel.util.DBConnection;

public class StudentDAO {

    public Student getStudentByUserId(int userId) {
        String sql = "SELECT * FROM Students WHERE user_id=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Student(
                        userId, "", "", "STUDENT",
                        rs.getInt("student_id"),
                        rs.getString("reg_no"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("current_course")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving student: " + e.getMessage());
        }
        return null;
    }
}
