package com.coursechange.gui;

import com.coursechange.dao.RequestDAO;

public class StudentDashboard {

    public void submitCourseChange(int studentId, int oldCourseId, int newCourseId) {
        RequestDAO dao = new RequestDAO();
        dao.submitRequest(studentId, oldCourseId, newCourseId);
        System.out.println("Request submitted");
    }
}
