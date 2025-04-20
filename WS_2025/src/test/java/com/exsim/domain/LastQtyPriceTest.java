
package com.exsim.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LastQtyPriceTest {

    @Test
    void testLastQtyPrice() {
        LastQtyPrice lqp = new LastQtyPrice(100.5, 200L);
        assertEquals(100.5, lqp.getLastPrice());
        assertEquals(200L, lqp.getLastExecutedQty());

        lqp.setLastPrice(105.0);
        lqp.setLastExecutedQty(250L);
        assertEquals(105.0, lqp.getLastPrice());
        assertEquals(250L, lqp.getLastExecutedQty());
    }
}
