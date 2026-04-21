package com.coursechange.model;

public class CourseChangeRequest {

    private int requestId;
    private int studentId;
    private int oldCourseId;
    private int newCourseId;
    private String status;

    public CourseChangeRequest(int requestId, int studentId,
            int oldCourseId, int newCourseId,
            String status) {
        this.requestId = requestId;
        this.studentId = studentId;
        this.oldCourseId = oldCourseId;
        this.newCourseId = newCourseId;
        this.status = status;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getStatus() {
        return status;
    }
}
