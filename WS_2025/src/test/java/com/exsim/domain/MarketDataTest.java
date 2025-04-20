
package com.exsim.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MarketDataTest {

    @Test
    void testMarketData() {
        MarketData data = new MarketData("AAPL", 99.5, 101.0, 105.0, 98.0, 100.0);
        assertEquals("AAPL", data.getSymbol());
        assertEquals(99.5, data.getBidprice());
        assertEquals(101.0, data.getAskprice());
        assertEquals(105.0, data.getHighprice());
        assertEquals(98.0, data.getLowprice());
        assertEquals(100.0, data.getLastprice());

        data.setLowprice(97.0);
        assertEquals(97.0, data.getLowprice());
    }
}
