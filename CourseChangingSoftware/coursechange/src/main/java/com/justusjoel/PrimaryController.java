package com.justusjoel;

import java.io.IOException;

import com.justusjoel.dao.CourseDAO;
import com.justusjoel.dao.RequestDAO;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextField txtStudentId;

    @FXML
    private ComboBox<String> cbCurrentCourse;

    @FXML
    private ComboBox<String> cbNewCourse;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblUsername;

    @FXML
    public void initialize() {
        // Display current user
        lblUsername.setText("👤 " + SessionManager.getCurrentUsername());
        
        cbCurrentCourse.getItems().addAll("CS101", "IT102", "ENG201");
        cbNewCourse.getItems().addAll("CS101", "IT102", "ENG201");
    }

    @FXML
    private void submitRequest() {
        try {
            int studentId = Integer.parseInt(txtStudentId.getText());

            CourseDAO courseDAO = new CourseDAO();
            int oldCourseId = courseDAO.getCourseIdByCode(cbCurrentCourse.getValue());
            int newCourseId = courseDAO.getCourseIdByCode(cbNewCourse.getValue());

            new RequestDAO().submitRequest(studentId, oldCourseId, newCourseId);

            lblStatus.setText("Request submitted successfully.");
        } catch (Exception e) {
            lblStatus.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleLogout() throws IOException {
        SessionManager.logout();
        App.setRoot("login");
    }



}
