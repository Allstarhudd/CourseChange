package com.justusjoel.model;

import java.time.LocalDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CourseChangeRequest {

    private final IntegerProperty requestId;
    private final IntegerProperty studentId;
    private final IntegerProperty oldCourseId;
    private final IntegerProperty newCourseId;
    private final StringProperty status;
    private final LocalDateTime requestDate;
    private final LocalDateTime decisionDate;
    private final StringProperty remarks;

    public CourseChangeRequest(int requestId, int studentId, int oldCourseId,
                               int newCourseId, String status, LocalDateTime requestDate,
                               LocalDateTime decisionDate, String remarks) {
        this.requestId = new SimpleIntegerProperty(requestId);
        this.studentId = new SimpleIntegerProperty(studentId);
        this.oldCourseId = new SimpleIntegerProperty(oldCourseId);
        this.newCourseId = new SimpleIntegerProperty(newCourseId);
        this.status = new SimpleStringProperty(status);
        this.requestDate = requestDate;
        this.decisionDate = decisionDate;
        this.remarks = new SimpleStringProperty(remarks);
    }

    // Property getters for TableView binding
    public IntegerProperty idProperty() {
        return requestId;
    }

    public IntegerProperty studentIdProperty() {
        return studentId;
    }

    public IntegerProperty oldCourseProperty() {
        return oldCourseId;
    }

    public IntegerProperty newCourseProperty() {
        return newCourseId;
    }

    public StringProperty statusProperty() {
        return status;
    }

    // Value getters for non-UI code
    public int getId() {
        return requestId.get();
    }

    public int getOldCourse() {
        return oldCourseId.get();
    }

    public int getNewCourse() {
        return newCourseId.get();
    }

    public int getStudentId() { 
        return studentId.get(); 
    }
    
    public String getStatus() { 
        return status.get(); 
    }
    
    public LocalDateTime getRequestDate() { 
        return requestDate; 
    }
    
    public LocalDateTime getDecisionDate() { 
        return decisionDate; 
    }
    
    public String getRemarks() { 
        return remarks.get(); 
    }
}
