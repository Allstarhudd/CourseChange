package com.justusjoel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.justusjoel.model.CourseChangeRequest;
import com.justusjoel.util.DBConnection;

public class RequestDAO {

    public void saveRequest(CourseChangeRequest request) throws SQLException {
    String sql = "INSERT INTO CourseChangeRequests (student_id, old_course_id, new_course_id, status, remarks) " +
                 "VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, request.getStudentId());
        ps.setInt(2, request.getOldCourse());
        ps.setInt(3, request.getNewCourse());
        ps.setString(4, request.getStatus());
        ps.setString(5, request.getRemarks());

        ps.executeUpdate();
    }
}

public void submitRequest(int studentId, int oldCourseId, int newCourseId, String remarks) throws SQLException {
        CourseChangeRequest request = new CourseChangeRequest(
                0,
                studentId,
                oldCourseId,
                newCourseId,
                "PENDING",
                LocalDateTime.now(),
                null,
                remarks
        );

    saveRequest(request);
}

public void submitRequest(int studentId, int oldCourseId, int newCourseId) throws SQLException {
    submitRequest(studentId, oldCourseId, newCourseId, null);
}

    public List<CourseChangeRequest> getPendingRequests() throws SQLException {
    String sql = """
        SELECT request_id, student_id, old_course_id, new_course_id, status, 
               request_date, decision_date, remarks
        FROM CourseChangeRequests
        WHERE status = 'PENDING'
        """;

    List<CourseChangeRequest> list = new ArrayList<>();

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            java.sql.Timestamp decisionTs = rs.getTimestamp("decision_date");
            LocalDateTime decisionDate = decisionTs != null ? decisionTs.toLocalDateTime() : null;
            
            CourseChangeRequest req = new CourseChangeRequest(
                    rs.getInt("request_id"),
                    rs.getInt("student_id"),
                    rs.getInt("old_course_id"),
                    rs.getInt("new_course_id"),
                    rs.getString("status"),
                    rs.getTimestamp("request_date").toLocalDateTime(),
                    decisionDate,
                    rs.getString("remarks")
            );
            list.add(req);
        }
    }
    return list;
}



    public boolean updateStatus(int requestId, String status, String remarks) throws SQLException {
        String sql = "UPDATE CourseChangeRequests SET status = ?, decision_date = GETDATE(), remarks = ? WHERE request_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setString(2, remarks != null ? remarks : "");
            ps.setInt(3, requestId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    public boolean updateStatus(int requestId, String status) throws SQLException {
        return updateStatus(requestId, status, null);
    }
}
