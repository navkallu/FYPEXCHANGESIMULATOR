package com.exsim.db;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
@Disabled
public class MarketDataDAOTest {

    @Test
    void testInsertMarketDataExecutesSQLWithoutErrors() throws Exception {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        try (MockedStatic<DatabaseConnection> mockedStatic = Mockito.mockStatic(DatabaseConnection.class)) {
            mockedStatic.when(DatabaseConnection::getConnection).thenReturn(mockConnection);

            MarketDataDAO marketDataDAO = new MarketDataDAO();

            assertDoesNotThrow(() -> marketDataDAO.insertMarketData(
                    "AAPL", 150.5, 151.0, 149.5, 150.0, 150.2, 1000L, 123456789L, 150.1, "NASDAQ"
            ));

            verify(mockStatement, times(1)).executeUpdate();
            verify(mockStatement, times(1)).close();
        }
    }
}
