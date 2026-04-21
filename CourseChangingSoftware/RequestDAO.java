package com.coursechange.dao;

import com.coursechange.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequestDAO {

    public void submitRequest(int studentId, int oldCourseId, int newCourseId) {
        String sql = """
            INSERT INTO CourseChangeRequests
            (student_id, old_course_id, new_course_id, status)
            VALUES (?, ?, ?, 'PENDING')
        """;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, oldCourseId);
            ps.setInt(3, newCourseId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
