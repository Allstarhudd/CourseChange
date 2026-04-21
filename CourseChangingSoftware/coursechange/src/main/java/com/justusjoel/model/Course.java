package com.justusjoel.model;

public class Course {

    @SuppressWarnings("FieldMayBeFinal")
    private int courseId;
    @SuppressWarnings("FieldMayBeFinal")
    private String courseCode;
    @SuppressWarnings("FieldMayBeFinal")
    private String courseName;
    @SuppressWarnings("FieldMayBeFinal")
    private String department;

    public Course(int courseId, String courseCode,
                  String courseName, String department) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.department = department;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDepartment() {
        return department;
    }
}
