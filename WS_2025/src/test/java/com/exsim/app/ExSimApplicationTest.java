
package com.exsim.app;

import com.exsim.service.MatchingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quickfix.FieldNotFound;
import quickfix.Message;
import quickfix.field.ClOrdID;
import quickfix.field.Price;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExSimApplicationTest {

    private ExSimApplication app;

    @BeforeEach
    void setUp() {
        app = new ExSimApplication();
    }

    @Test
    void testGetStringFieldValueReturnsDefaultWhenFieldMissing() throws FieldNotFound {
        Message mockMessage = mock(Message.class);
        when(mockMessage.getString(ClOrdID.FIELD)).thenThrow(new FieldNotFound(""));

        String result = app.getStringFieldValue(mockMessage, ClOrdID.FIELD);
        assertEquals("", result);
    }

    @Test
    void testGetDoubleFieldValueReturnsDefaultWhenFieldMissing() throws FieldNotFound {
        Message mockMessage = mock(Message.class);
        when(mockMessage.getDouble(Price.FIELD)).thenThrow(new FieldNotFound(""));

        double result = app.getDoubleFieldValue(mockMessage, Price.FIELD);
        assertEquals(-9999.0, result);
    }

    @Test
    void testOrderMatcherReturnsInstance() {
        assertNotNull(app.orderMatcher());
        assertEquals(MatchingService.class, app.orderMatcher().getClass());
    }
}
