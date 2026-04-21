package com.justusjoel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.justusjoel.dao.RequestDAO;
import com.justusjoel.model.CourseChangeRequest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;

public class SecondaryController {

    @FXML
    private TableView<CourseChangeRequest> tableRequests;

    @FXML
    private TableColumn<CourseChangeRequest, Integer> colId;

    @FXML
    private TableColumn<CourseChangeRequest, Integer> colStudentId;
    
    @FXML
    private TableColumn<CourseChangeRequest, Integer> colCurrentCourse;

    @FXML
    private TableColumn<CourseChangeRequest, Integer> colNewCourse;

    @FXML
    private TableColumn<CourseChangeRequest, String> colStatus;

    @FXML
    private Label statusLabel;

    @FXML
    private Label lblUsername;

    private CourseChangeRequest selectedRequest = null;

    @FXML
    public void initialize() {
        try {
            // Display current user
            lblUsername.setText("👤 " + SessionManager.getCurrentUsername() + " (ADMIN)");
            
            // Use Callback to directly bind to Observable properties with explicit casting
            colId.setCellValueFactory(cellData -> (javafx.beans.value.ObservableValue<Integer>) (javafx.beans.value.ObservableValue<?>) cellData.getValue().idProperty());
            colStudentId.setCellValueFactory(cellData -> (javafx.beans.value.ObservableValue<Integer>) (javafx.beans.value.ObservableValue<?>) cellData.getValue().studentIdProperty());
            colCurrentCourse.setCellValueFactory(cellData -> (javafx.beans.value.ObservableValue<Integer>) (javafx.beans.value.ObservableValue<?>) cellData.getValue().oldCourseProperty());
            colNewCourse.setCellValueFactory(cellData -> (javafx.beans.value.ObservableValue<Integer>) (javafx.beans.value.ObservableValue<?>) cellData.getValue().newCourseProperty());
            colStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
            
            System.out.println("Table columns bound successfully");
            
            // Allow table row selection with visual feedback
            tableRequests.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    selectedRequest = newVal;
                    if (newVal != null) {
                        System.out.println("Selected request ID: " + newVal.getId() + 
                                         ", Student: " + newVal.getStudentId());
                    }
                }
            );
            
            loadRequests();
        } catch (Exception e) {
            System.err.println("❌ Error initializing admin panel: " + e.getMessage());
            e.printStackTrace();
            statusLabel.setText("Error loading admin panel: " + e.getMessage());
        }
    }

    private void loadRequests() {
        try {
            System.out.println("\n=== Loading Requests ===");
            statusLabel.setText("Loading requests from database...");
            statusLabel.setStyle("-fx-text-fill: #0066cc;");
            
            List<CourseChangeRequest> list = new RequestDAO().getPendingRequests();
            
            if (list == null) {
                System.out.println("ERROR: getPendingRequests() returned null");
                statusLabel.setText("ERROR: Database query returned null. Check logs.");
                statusLabel.setStyle("-fx-text-fill: #cc0000; -fx-font-weight: bold;");
                showAlert("Database Error", "Query returned null. Check database connection.");
                return;
            }
            
            System.out.println("Retrieved " + list.size() + " pending requests from database");
            
            if (list.isEmpty()) {
                System.out.println("No pending requests found - table will be empty");
                statusLabel.setText("No pending requests found. (0 records)");
                statusLabel.setStyle("-fx-text-fill: #ff6600;");
                ObservableList<CourseChangeRequest> data = FXCollections.observableArrayList();
                tableRequests.setItems(data);
                return;
            }
            
            System.out.println("Loading " + list.size() + " requests into table:");
            for (int i = 0; i < list.size(); i++) {
                CourseChangeRequest req = list.get(i);
                System.out.printf("  Row %d: ID=%d, Student=%d, From=%d, To=%d, Status=%s%n",
                    (i+1), req.getId(), req.getStudentId(), req.getOldCourse(), 
                    req.getNewCourse(), req.getStatus());
            }
            
            ObservableList<CourseChangeRequest> data = FXCollections.observableArrayList(list);
            tableRequests.setItems(data);
            
            System.out.println("Table populated successfully!");
            statusLabel.setText("✓ Loaded " + list.size() + " pending request(s)");
            statusLabel.setStyle("-fx-text-fill: #00aa00; -fx-font-weight: bold;");
            
        } catch (SQLException e) {
            System.err.println("\n❌ SQL ERROR loading requests:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
            
            statusLabel.setText("❌ Database Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: #cc0000; -fx-font-weight: bold;");
            showAlert("Database Error", "Failed to load requests:\n" + e.getMessage());
        }
    }
    
    @FXML
    private void refreshTable() {
        System.out.println("\nManually refreshing table...");
        tableRequests.getItems().clear();
        loadRequests();
    }
    
    @FXML
    private void approveRequest() {
        if (selectedRequest == null) {
            showAlert("No Selection", "Please select a request to approve.");
            return;
        }
        
        try {
            System.out.println("Approving request ID: " + selectedRequest.getId());
            boolean success = new RequestDAO().updateStatus(selectedRequest.getId(), "APPROVED");
            
            if (success) {
                System.out.println("Request approved successfully. Refreshing table...");
                showAlert("Success", "Request approved successfully!");
                loadRequests(); // Refresh the table
            } else {
                showAlert("Error", "Failed to approve request. Request not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error approving request: " + e.getMessage());
            e.printStackTrace();
            showAlert("Database Error", "Failed to approve request: " + e.getMessage());
        }
    }
    
    @FXML
    private void rejectRequest() {
        if (selectedRequest == null) {
            showAlert("No Selection", "Please select a request to reject.");
            return;
        }
        
        // Ask for rejection reason/remarks
        String remarks = askForRemarks("Reject Request", "Enter reason for rejection:");
        if (remarks == null || remarks.trim().isEmpty()) {
            showAlert("Rejection Reason Required", "Please provide a reason for rejection.");
            return;
        }
        
        try {
            System.out.println("Rejecting request ID: " + selectedRequest.getId());
            boolean success = new RequestDAO().updateStatus(selectedRequest.getId(), "REJECTED", remarks);
            
            if (success) {
                System.out.println("Request rejected successfully. Refreshing table...");
                showAlert("Success", "Request rejected successfully!");
                loadRequests(); // Refresh the table
            } else {
                showAlert("Error", "Failed to reject request. Request not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error rejecting request: " + e.getMessage());
            e.printStackTrace();
            showAlert("Database Error", "Failed to reject request: " + e.getMessage());
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private String askForRemarks(String title, String message) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(message);
        return dialog.showAndWait().orElse(null);
    }

    @FXML
    private void handleLogout() throws IOException {
        SessionManager.logout();
        App.setRoot("login");
    }
}
