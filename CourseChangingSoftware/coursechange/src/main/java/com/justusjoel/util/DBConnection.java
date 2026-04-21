package com.justusjoel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
    "jdbc:sqlserver://localhost:1433;databaseName=CourseChangeDB;trustServerCertificate=true";
    private static final String USER = "course_user";
    private static final String PASSWORD = "StrongPassword123";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}