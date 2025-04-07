package com.exsim.db;

import com.exsim.app.SimulatorMain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    //private static final String JDBC_URL = "jdbc:h2:./data/orderbook";
    //private static final String JDBC_URL = "jdbc:h2:tcp://192.168.102.169:9092/./data/orderbook";
    //private static final String USER = "sa";
    //private static final String PASSWORD = "";
    private static String JDBC_URL = "jdbc:h2:tcp://192.168.102.169:9092/./data/orderbook";
    private static String USER = "sa";
    private static String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        JDBC_URL = SimulatorMain.DBURL+"/./data/orderbook";
        //USER = SimulatorMain.DBUSER;
        //PASSWORD = SimulatorMain.DBPASSWORD;

        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}

