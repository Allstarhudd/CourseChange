package com.coursechange.model;

public class Student extends User {

    private int studentId;
    private String regNo;
    private String fullName;
    private String email;
    private String currentCourse;

    public Student(int userId, String username, String passwordHash, String role,
            int studentId, String regNo, String fullName,
            String email, String currentCourse) {

        super(userId, username, passwordHash, role);
        this.studentId = studentId;
        this.regNo = regNo;
        this.fullName = fullName;
        this.email = email;
        this.currentCourse = currentCourse;
    }

    public int getStudentId() {
        return studentId;
    }
}
