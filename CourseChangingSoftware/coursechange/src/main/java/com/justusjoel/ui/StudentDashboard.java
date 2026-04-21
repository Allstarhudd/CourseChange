package com.justusjoel.ui;

import java.sql.SQLException;

import com.justusjoel.dao.RequestDAO;

public class StudentDashboard {

    public void submitCourseChange(int studentId, int oldCourseId, int newCourseId) throws SQLException {
        RequestDAO dao = new RequestDAO();
        dao.submitRequest(studentId, oldCourseId, newCourseId);
        System.out.println("Request submitted");
    }
}
