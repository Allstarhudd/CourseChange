package com.justusjoel;

import java.io.IOException;
import java.sql.SQLException;

import com.justusjoel.dao.UserDAO;
import com.justusjoel.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblError;

    @FXML
    private RadioButton rbStudent;

    @FXML
    private RadioButton rbAdmin;

    @FXML
    public void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            lblError.setText("❌ Please enter username and password");
            return;
        }

        try {
            // Use plain password for authentication
            System.out.println("DEBUG: Attempting login");
            System.out.println("  Username: " + username);
            System.out.println("  Password: " + password);

            // Authenticate user
            UserDAO userDAO = new UserDAO();
            User user = userDAO.authenticate(username, password);

            if (user == null) {
                System.out.println("DEBUG: Authentication failed - user not found");
                lblError.setText("❌ Invalid username or password");
                return;
            }
            
            System.out.println("DEBUG: User found!");
            System.out.println("  User role from DB: " + user.getRole());

            // Check role matches selection
                String selectedRole;
                if (rbStudent.isSelected()) {
                    selectedRole = "STUDENT";
                } else if (rbAdmin.isSelected()) {
                    selectedRole = "ADMIN";
                } else {
                    lblError.setText("❌ Please select a role");
                    return;
                }
            
            System.out.println("  Selected role: " + selectedRole);

            if (!user.getRole().equalsIgnoreCase(selectedRole)) {
                System.out.println("DEBUG: Role mismatch");
                lblError.setText("❌ User role does not match selected role");
                return;
            }

            // Set current user in session
            SessionManager.setCurrentUser(user);

            System.out.println("✓ Login successful: " + username + " (" + user.getRole() + ")");

            // Navigate based on role
            if (user.getRole().equalsIgnoreCase("STUDENT")) {
                App.setRoot("primary");
            } else if (user.getRole().equalsIgnoreCase("ADMIN")) {
                App.setRoot("secondary");
            }

        } catch (SQLException e) {
            System.err.println("Database error during login: " + e.getMessage());
            lblError.setText("❌ Database error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Navigation error: " + e.getMessage());
            lblError.setText("❌ Navigation error");
        }
    }

    @FXML
    public void initialize() {
        // Clear error message when user types
        txtUsername.textProperty().addListener((obs, oldVal, newVal) -> lblError.setText(""));
        txtPassword.textProperty().addListener((obs, oldVal, newVal) -> lblError.setText(""));
    }
}
