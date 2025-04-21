package com.exsim.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/orderbook_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}*/

public class DatabaseConnection {
    public static Connection getConnection() {
        try {
            String url = "jdbc:postgresql://localhost:5432/orderbook_db";
            //String user = "postgres";
            //String password = "postgres";
            String user = "sukrutirai";
            String password = "";
            System.out.println("Connecting with URL: " + url + ", user: " + user);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
