package com.accessmanagement.models;

import java.sql.*;

public class Database {
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/user_access_management",
                    "postgres", "akhil");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
//    private static final String URL = "jdbc:postgresql://localhost:5432/user_access_management";
//    private static final String USER = "postgres";
//    private static final String PASSWORD = "akhil";
//
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USER, PASSWORD);
//    }
}
