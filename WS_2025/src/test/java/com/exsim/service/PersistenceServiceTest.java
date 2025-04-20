
package com.exsim.service;

import com.exsim.domain.Market;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceServiceTest {

    private HashMap<String, Market> mockMarkets;

    @BeforeEach
    void setUp() {
        mockMarkets = new HashMap<>();
        Market mockMarket = new Market(); // Assuming Market has a no-arg constructor

        // Add mock bid/ask orders if needed via mockMarket.getBidOrders().add(...)

        mockMarkets.put("AAPL", mockMarket);
    }

    @Test
    void testDisplayOrderBookReturnsJson() {
        String json = PersistenceService.displayOrderBook(mockMarkets);
        assertNotNull(json, "JSON string should not be null");
        assertTrue(json.contains("AAPL"), "JSON string should contain market symbol");
    }

    @Test
    void testUpdateOrderBookDBRunsWithoutException() {
        assertDoesNotThrow(() -> PersistenceService.updateOrderBookDB(mockMarkets),
                "Should not throw exceptions when updating order book DB");
    }
}
