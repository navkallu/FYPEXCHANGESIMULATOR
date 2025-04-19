package com.exsim.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Setup {
    public static void createTable() {
        String dropTableOrderBookSQL = "DROP TABLE IF EXISTS orderbook;";
        String createTableOrderBookSQL = "CREATE TABLE IF NOT EXISTS orderbook (" +
                "exchange VARCHAR(10), " +
                "symbol VARCHAR(10) PRIMARY KEY, " +
                "bid_orders TEXT, " +
                "ask_orders TEXT);";

        String dropTableMarketDataSQL = "DROP TABLE IF EXISTS marketdata;";
        String createTableMarketDataSQL = "CREATE TABLE IF NOT EXISTS marketdata (" +
                "exchange VARCHAR(10), " +
                "symbol VARCHAR(255) PRIMARY KEY, " +
                "bidprice NUMERIC(15,2), " +
                "askprice NUMERIC(15,2), " +
                "highprice NUMERIC(15,2), " +
                "lowprice NUMERIC(15,2), " +
                "lastprice NUMERIC(15,2), " +
                "executedqty BIGINT, " +
                "totalexecutedqty BIGINT, " +
                "avgprice NUMERIC(15,2), " +
                "isopen VARCHAR(10));";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(dropTableOrderBookSQL);
            statement.execute(dropTableMarketDataSQL);
            statement.execute(createTableOrderBookSQL);
            statement.execute(createTableMarketDataSQL);

            System.out.println("Tables created successfully in PostgreSQL!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
