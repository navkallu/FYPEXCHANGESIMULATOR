package com.exsim.db;

import com.exsim.app.SimulatorMain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MarketDataDAO {
    public void insertMarketData(String symbol, double bidprice, double askprice, double highprice,
                                 double lowprice, double lastprice, long executedQty, long totalExecutedQty, double avgPrice, String isOpen) {
        String upsertSQL = "INSERT INTO marketdata (exchange, symbol, bidprice, askprice, highprice, lowprice, lastprice, executedqty, totalexecutedqty, avgprice, isopen) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT (symbol) DO UPDATE SET " +
                "bidprice = EXCLUDED.bidprice, " +
                "askprice = EXCLUDED.askprice, " +
                "highprice = EXCLUDED.highprice, " +
                "lowprice = EXCLUDED.lowprice, " +
                "lastprice = EXCLUDED.lastprice, " +
                "executedqty = EXCLUDED.executedqty, " +
                "totalexecutedqty = EXCLUDED.totalexecutedqty, " +
                "avgprice = EXCLUDED.avgprice, " +
                "isopen = EXCLUDED.isopen;";

        String exchange = SimulatorMain.EXCHANGE;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(upsertSQL)) {
            preparedStatement.setString(1, exchange);
            preparedStatement.setString(2, symbol);
            preparedStatement.setDouble(3, bidprice);
            preparedStatement.setDouble(4, askprice);
            preparedStatement.setDouble(5, highprice);
            preparedStatement.setDouble(6, lowprice);
            preparedStatement.setDouble(7, lastprice);
            preparedStatement.setLong(8, executedQty);
            preparedStatement.setLong(9, totalExecutedQty);
            preparedStatement.setDouble(10, avgPrice);
            preparedStatement.setString(11, isOpen);
            preparedStatement.executeUpdate();

            System.out.println("MarketData inserted successfully into PostgreSQL!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
