
package com.exsim.service;

import com.exsim.domain.PriceData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceUpdateServiceTest {

    private PriceUpdateService priceUpdateService;

    @BeforeEach
    void setUp() {
        priceUpdateService = PriceUpdateService.getInstance();
    }

    @Test
    void testSingletonInstance() {
        PriceUpdateService anotherInstance = PriceUpdateService.getInstance();
        assertSame(priceUpdateService, anotherInstance, "Expected singleton instance");
    }

    @Test
    void testGetPriceDataReturnsSameObjectForSameSymbol() {
        PriceData pd1 = priceUpdateService.getPriceData("AAPL");
        PriceData pd2 = priceUpdateService.getPriceData("AAPL");

        assertSame(pd1, pd2, "Expected the same PriceData instance for the same symbol");
    }

    @Test
    void testGetPriceDataInitializesNewPriceData() {
        PriceData pd = priceUpdateService.getPriceData("GOOG");

        assertNotNull(pd, "PriceData should not be null");
        assertEquals(0.0, pd.getHighPrice(), "Initial high price should be 0.0");
        assertEquals(0.0, pd.getLowPrice(), "Initial low price should be 0.0");
        assertEquals(0.0, pd.getLastPrice(), "Initial last price should be 0.0");
    }
}
