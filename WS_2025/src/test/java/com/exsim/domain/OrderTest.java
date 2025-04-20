
package com.exsim.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testOrderExecution() {
        Order order = new Order("CL1", "AAPL", "OWNER", "TARGET", '1', '2', 100.0, 50L, "ORIG");
        assertFalse(order.isClosed());
        assertFalse(order.isFilled());

        order.execute(100.0, 30L);
        assertEquals(20L, order.getOpenQuantity());
        assertEquals(30L, order.getExecutedQuantity());

        order.execute(100.0, 20L);
        assertTrue(order.isFilled());
        assertTrue(order.isClosed());
    }
}
