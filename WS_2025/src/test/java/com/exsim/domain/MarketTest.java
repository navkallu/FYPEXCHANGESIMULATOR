
package com.exsim.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MarketTest {

    private Market market;

    @BeforeEach
    public void setUp() {
        market = new Market();
    }

    @Test
    public void testBidOrdersAccessibleAndEmpty() {
        try {
            Field bidField = Market.class.getDeclaredField("bidOrders");
            bidField.setAccessible(true);
            Object bidOrders = bidField.get(market);

            assertNotNull(bidOrders, "bidOrders should not be null");
            assertTrue(bidOrders instanceof List, "bidOrders should be a List");
            assertTrue(((List<?>) bidOrders).isEmpty(), "bidOrders should be initially empty");
        } catch (Exception e) {
            fail("Reflection error for bidOrders: " + e.getMessage());
        }
    }

    @Test
    public void testAskOrdersAccessibleAndEmpty() {
        try {
            Field askField = Market.class.getDeclaredField("askOrders");
            askField.setAccessible(true);
            Object askOrders = askField.get(market);

            assertNotNull(askOrders, "askOrders should not be null");
            assertTrue(askOrders instanceof List, "askOrders should be a List");
            assertTrue(((List<?>) askOrders).isEmpty(), "askOrders should be initially empty");
        } catch (Exception e) {
            fail("Reflection error for askOrders: " + e.getMessage());
        }
    }
}
