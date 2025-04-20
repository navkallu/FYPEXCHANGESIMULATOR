
package com.exsim.service;

import com.exsim.domain.MarketData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MarketDataLoadServiceTest {

    @BeforeEach
    void setUp() throws Exception {
        // Create properly constructed mock MarketData
        MarketData mockData = new MarketData("AAPL", 99.5, 101.0, 105.0, 98.0, 100.0);

        HashMap<String, MarketData> mockMap = new HashMap<>();
        mockMap.put("AAPL", mockData);

        // Inject into private static field using reflection
        java.lang.reflect.Field field = MarketDataLoadService.class.getDeclaredField("marketDataMap");
        field.setAccessible(true);
        field.set(null, mockMap);
    }

    @Test
    void testGetBidpriceReturnsCorrectValue() {
        double bid = MarketDataLoadService.getBidprice("AAPL");
        assertEquals(99.5, bid, "Bid price should match mock data");
    }

    @Test
    void testGetAskpriceReturnsCorrectValue() {
        double ask = MarketDataLoadService.getAskprice("AAPL");
        assertEquals(101.0, ask, "Ask price should match mock data");
    }
}
