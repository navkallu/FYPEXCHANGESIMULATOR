
package com.exsim.db;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DatabaseConnectionTest {

    @Test
    void testGetConnectionDelegatesToDriverManager() throws SQLException {
        Connection mockConnection = mock(Connection.class);

        try (MockedStatic<DriverManager> mockedDriverManager = mockStatic(DriverManager.class)) {
            mockedDriverManager.when(() ->
                    DriverManager.getConnection("jdbc:postgresql://localhost:5432/orderbook_db", "postgres", "postgres")
            ).thenReturn(mockConnection);

            Connection conn = DatabaseConnection.getConnection();

            assertNotNull(conn, "Connection should not be null");
            assertSame(mockConnection, conn, "Should return the mocked connection");
            mockedDriverManager.verify(() ->
                    DriverManager.getConnection("jdbc:postgresql://localhost:5432/orderbook_db", "postgres", "postgres")
            );
        }
    }
}
