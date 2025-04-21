package com.exsim.db;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.junit.jupiter.api.Disabled;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;
@Disabled
public class DatabaseConnectionTest {

    @Test
    void testGetConnectionReturnsValidConnection() {
        Connection mockConnection = mock(Connection.class);

        // EXACT match to what DatabaseConnection calls
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        try (MockedStatic<DriverManager> driverManagerMockedStatic = Mockito.mockStatic(DriverManager.class)) {
            driverManagerMockedStatic
                    .when(() -> DriverManager.getConnection(url, user, password))
                    .thenReturn(mockConnection);

            Connection actualConnection = DatabaseConnection.getConnection();
            assertSame(mockConnection, actualConnection, "Expected the mocked connection");
        }
    }
}
