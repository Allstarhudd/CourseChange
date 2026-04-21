package com.coursechange.model;

public class Course {

    private int courseId;
    private String courseCode;
    private String courseName;
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

    public String getCourseName() {
        return courseName;
    }
}
