package com.justusjoel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.justusjoel.util.DBConnection;

public class CourseDAO {

    public int getCourseIdByCode(String courseCode) throws Exception {
        String sql = "SELECT course_id FROM Courses WHERE course_code = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, courseCode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("course_id");
            } else {
                throw new Exception("Course code not found: " + courseCode);
            }
        }
    }
}