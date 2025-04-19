package com.exsim.db;

import com.exsim.app.SimulatorMain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderBookDAO {
    public void insertOrderBook(String symbol, String bidOrders, String askOrders) {
        String upsertSQL = "INSERT INTO orderbook (exchange, symbol, bid_orders, ask_orders) " +
                "VALUES (?, ?, ?, ?) " +
                "ON CONFLICT (symbol) DO UPDATE SET " +
                "bid_orders = EXCLUDED.bid_orders, " +
                "ask_orders = EXCLUDED.ask_orders;";

        String exchange = SimulatorMain.EXCHANGE;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(upsertSQL)) {
            preparedStatement.setString(1, exchange);
            preparedStatement.setString(2, symbol);
            preparedStatement.setString(3, bidOrders);
            preparedStatement.setString(4, askOrders);
            preparedStatement.executeUpdate();

            System.out.println("OrderBook inserted successfully into PostgreSQL!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

