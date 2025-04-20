
package com.exsim.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MarketDataDAOTest {

    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;

    @BeforeEach
    void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    }

    @Test
    void testInsertMarketDataExecutesSQLWithoutErrors() throws SQLException {
        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {

            dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConnection);

            MarketDataDAO dao = new MarketDataDAO();

            // Replace SimulatorMain.EXCHANGE with a literal "HK"
            dao.insertMarketData("AAPL", 99.5, 101.0, 105.0, 98.0, 100.0, 20L, 200L, 100.25, "true");

            verify(mockPreparedStatement, times(1)).executeUpdate();
        }
    }
}
