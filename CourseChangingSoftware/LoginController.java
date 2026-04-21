package com.coursechange.gui;

import com.coursechange.dao.UserDAO;
import com.coursechange.model.User;

public class LoginController {

    public void login(String username, String password) {
        UserDAO dao = new UserDAO();
        User user = dao.authenticate(username, password);

        if (user != null) {
            System.out.println("Login successful: " + user.getRole());
        } else {
            System.out.println("Invalid credentials");
        }
    }
}
