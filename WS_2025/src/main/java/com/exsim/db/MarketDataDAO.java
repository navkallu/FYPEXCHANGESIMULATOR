package com.exsim.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MarketDataDAO {
    public void insertMarketData(String symbol, double bidprice, double askprice,double highprice, double lowprice, double lastprice ) {
        String mergeSQL = "MERGE INTO marketdata (symbol, bidprice, askprice, highprice,lowprice ,lastprice) " +
                "KEY (symbol) VALUES (?, ?, ?, ?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(mergeSQL)) {
            preparedStatement.setString(1, symbol);
            preparedStatement.setDouble(2, bidprice);
            preparedStatement.setDouble(3, askprice);
            preparedStatement.setDouble(4, highprice);
            preparedStatement.setDouble(5, lowprice);
            preparedStatement.setDouble(6, lastprice);
            preparedStatement.executeUpdate();
            System.out.println("MarketData inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
