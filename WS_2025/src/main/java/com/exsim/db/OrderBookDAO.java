package com.exsim.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderBookDAO {
    public void insertOrderBook(String symbol, String bidOrders, String askOrders) {
       // String insertSQL = "INSERT INTO orderbook (symbol, bid_orders, ask_orders) VALUES (?, ?, ?)";
        String mergeSQL = "MERGE INTO orderbook (symbol, bid_orders, ask_orders) " +
                "KEY (symbol) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(mergeSQL)) {
             preparedStatement.setString(1, symbol);
             preparedStatement.setString(2, bidOrders);
             preparedStatement.setString(3, askOrders);
             preparedStatement.executeUpdate();
             System.out.println("OrderBook inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

