package com.ying.tjava.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionExample {

    private static final String URL = "jdbc:mysql://localhost:3306/tjava";
    private static final String USER = "root";
    private static final String PASSWORD = "066311";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database successfully.");
            // ... 进行数据库操作 ...
            connection.close();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }
}