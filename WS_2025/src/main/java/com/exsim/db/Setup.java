package com.exsim.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Setup {
    public static void createTable() {

        String dropTableOrderBookSQL = "DROP TABLE IF EXISTS orderbook;";
        String createTableOrderBookSQL = "CREATE TABLE IF NOT EXISTS orderbook (" +
                "exchange VARCHAR(10) , " +
                "symbol VARCHAR(10) NOT NULL PRIMARY KEY, " +
                "bid_orders CLOB, " +
                "ask_orders CLOB)";
        String dropTableMarketDataSQL = "DROP TABLE IF EXISTS marketdata;";
        String createTableMarketDataSQL = "CREATE TABLE marketdata (" +
                "exchange VARCHAR(10) , " +
                "symbol VARCHAR(255), " +
                "bidprice DECIMAL(15, 2), " +
                "askprice DECIMAL(15, 2), " +
                "highprice DECIMAL(15, 2), " +
                "lowprice DECIMAL(15, 2), " +
                "lastprice DECIMAL(15, 2)," +
                "executedqty BIGINT," +
                "totalexecutedqty BIGINT," +
                "avgprice DECIMAL(15,2)," +
                "isopen VARCHAR(10))";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
             statement.execute(dropTableOrderBookSQL);
             statement.execute(dropTableMarketDataSQL);
             statement.execute(createTableOrderBookSQL);
             statement.execute(createTableMarketDataSQL);

            System.out.println("Table created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
