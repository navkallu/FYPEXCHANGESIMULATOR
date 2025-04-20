
package com.exsim.service;

import com.exsim.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MatchingServiceTest {

    private MatchingService matchingService;

    @BeforeEach
    void setUp() {
        matchingService = MatchingService.getInstance();
    }

    @Test
    void testSingletonInstance() {
        MatchingService anotherInstance = MatchingService.getInstance();
        assertSame(matchingService, anotherInstance, "Expected the same singleton instance");
    }

    @Test
    void testMatchDelegation() {
        Order mockOrder1 = new Order("CL1", "TSLA", "OWNER1", "TARGET1", '1', '2', 100.0, 10L, "ORIG1");
        Order mockOrder2 = new Order("CL2", "TSLA", "OWNER2", "TARGET2", '2', '2', 101.0, 15L, "ORIG2");

        ArrayList<Order> orders = new ArrayList<>();
        orders.add(mockOrder1);
        orders.add(mockOrder2);

        assertDoesNotThrow(() -> matchingService.match("TSLA", orders),
                "Matching should not throw exceptions");
    }
}
