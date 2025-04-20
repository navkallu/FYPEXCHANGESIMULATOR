
package com.exsim.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.exsim.app.SimulatorMain;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderBookDAOTest {

    private OrderBookDAO dao;
    private Connection mockConnection;
    private PreparedStatement mockStatement;

    @BeforeEach
    void setUp() throws Exception {
        dao = new OrderBookDAO();
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);

        // Mock static method DatabaseConnection.getConnection()
        mockStatic(DatabaseConnection.class).when(DatabaseConnection::getConnection).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
    }

    @Test
    void testInsertOrderBookExecutesStatement() throws Exception {
        String symbol = "AAPL";
        String bidOrders = "[{price:100}]";
        String askOrders = "[{price:105}]";

        dao.insertOrderBook(symbol, bidOrders, askOrders);

        verify(mockStatement).setString(1, SimulatorMain.EXCHANGE);
        verify(mockStatement).setString(2, symbol);
        verify(mockStatement).setString(3, bidOrders);
        verify(mockStatement).setString(4, askOrders);
        verify(mockStatement).executeUpdate();
    }
}
