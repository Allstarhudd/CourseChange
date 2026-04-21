package com.justusjoel;

import com.justusjoel.model.User;

/**
 * Manages the current user session
 */
public class SessionManager {
    private static User currentUser = null;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static int getCurrentUserId() {
        return currentUser != null ? currentUser.getUserId() : -1;
    }

    public static String getCurrentUsername() {
        return currentUser != null ? currentUser.getUsername() : "Unknown";
    }

    public static String getCurrentRole() {
        return currentUser != null ? currentUser.getRole() : null;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static boolean isAdmin() {
        return currentUser != null && currentUser.getRole().equalsIgnoreCase("ADMIN");
    }

    public static boolean isStudent() {
        return currentUser != null && currentUser.getRole().equalsIgnoreCase("STUDENT");
    }

    public static void logout() {
        currentUser = null;
    }
}
