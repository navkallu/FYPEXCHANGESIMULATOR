package com.exsim.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Setup {
    public static void createTable() {
 /*       String createTableSQL = "CREATE TABLE IF NOT EXISTS orderbook (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "symbol VARCHAR(10) NOT NULL, " +
                "bid_orders CLOB, " +
                "ask_orders CLOB)";*/
        String createTableSQL = "CREATE TABLE IF NOT EXISTS orderbook (" +
                "symbol VARCHAR(10) NOT NULL PRIMARY KEY, " +
                "bid_orders CLOB, " +
                "ask_orders CLOB)";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
