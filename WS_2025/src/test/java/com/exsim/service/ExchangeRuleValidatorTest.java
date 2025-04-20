
package com.exsim.service;

import com.exsim.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeRuleValidatorTest {

    private ExchangeRuleValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ExchangeRuleValidator();
    }

    @Test
    void testValidateOrderForNonHKExchangeReturnsTrue() {
        Order order = new Order("CL1", "AAPL", "OWNER1", "TARGET1", '1', '2', 100.0, 10L, "ORIG1");
        boolean result = validator.validateOrder("NYSE", order);
        assertTrue(result, "Validation should pass for non-HK exchanges");
    }

    @Test
    void testValidateOrderForNonExistentMarketReturnsTrue() {
        Order order = new Order("CL2", "UNKNOWN", "OWNER2", "TARGET2", '1', '2', 101.0, 5L, "ORIG2");
        boolean result = validator.validateOrder("HK", order);
        assertTrue(result, "Validation should pass if market does not exist");
    }

    // More tests can be added when Market mocking is implemented
}
