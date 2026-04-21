package com.justusjoel.model;

public class Student extends User {

    @SuppressWarnings("FieldMayBeFinal")
    private int studentId;
    @SuppressWarnings("FieldMayBeFinal")
    private String regNo;
    @SuppressWarnings("FieldMayBeFinal")
    private String fullName;
    @SuppressWarnings("FieldMayBeFinal")
    private String email;
    @SuppressWarnings("FieldMayBeFinal")
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
    public String getRegNo() {
        return regNo;
    }
    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }
    public String getCurrentCourse() {
        return currentCourse;
    }
}