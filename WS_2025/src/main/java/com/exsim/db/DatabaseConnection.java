package com.exsim.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    //private static final String JDBC_URL = "jdbc:h2:./data/orderbook";
    private static final String JDBC_URL = "jdbc:h2:tcp://192.168.102.169:9092/./data/orderbook";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}

