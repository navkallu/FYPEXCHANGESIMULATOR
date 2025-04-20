
package com.exsim.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PriceDataTest {

    @Test
    void testPriceData() {
        PriceData pd = new PriceData();
        pd.setLastPrice(100.0);
        pd.setLowPrice(98.0);
        pd.setHighPrice(105.0);
        pd.setAvgPrice(101.0);
        pd.setLastExecutedQty(20L);
        pd.setTotalExecutedQty(200L);
        pd.setOpenOrders("Y");

        assertEquals(100.0, pd.getLastPrice());
        assertEquals(98.0, pd.getLowPrice());
        assertEquals(105.0, pd.getHighPrice());
        assertEquals(101.0, pd.getAvgPrice());
        assertEquals(20L, pd.getLastExecutedQty());
        assertEquals(200L, pd.getTotalExecutedQty());
        assertEquals("Y", pd.getOpenOrders());
    }
}
