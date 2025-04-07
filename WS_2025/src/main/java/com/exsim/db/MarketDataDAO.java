package com.exsim.db;

import com.exsim.app.SimulatorMain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MarketDataDAO {
    public void insertMarketData(String symbol, double bidprice, double askprice,double highprice, double lowprice, double lastprice ) {
        String mergeSQL = "MERGE INTO marketdata (exchange, symbol, bidprice, askprice, highprice,lowprice ,lastprice) " +
                "KEY (symbol) VALUES (?,?, ?, ?, ?,?,?)";

        String exchange = SimulatorMain.EXCHANGE;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(mergeSQL)) {
            preparedStatement.setString(1, exchange);
            preparedStatement.setString(2, symbol);
            preparedStatement.setDouble(3, bidprice);
            preparedStatement.setDouble(4, askprice);
            preparedStatement.setDouble(5, highprice);
            preparedStatement.setDouble(6, lowprice);
            preparedStatement.setDouble(7, lastprice);
            preparedStatement.executeUpdate();
            System.out.println("MarketData inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
