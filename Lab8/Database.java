package org.example;

import java.sql.Connection;
import java.sql.*;

public class Database {
    private static final String URL =
            "jdbc:mysql://localhost:3306/lab8";
    private static final String USER = "root";
    private static final String PASSWORD = "SarahDaniel11.";
    private static Connection connection = null;
    protected Database() {}

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD); // FIXED
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }


}
