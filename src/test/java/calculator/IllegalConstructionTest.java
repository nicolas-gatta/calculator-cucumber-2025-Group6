package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IllegalConstructionTest {

    @Test
    void testDefaultConstructor() {
        IllegalConstruction exception = new IllegalConstruction();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        String msg = "Invalid expression";
        IllegalConstruction exception = new IllegalConstruction(msg);
        assertEquals(msg, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        Throwable cause = new RuntimeException("Underlying cause");
        IllegalConstruction exception = new IllegalConstruction(cause);
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage()); // Default message is cause.toString()
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String msg = "Invalid expression";
        Throwable cause = new RuntimeException("Cause");
        IllegalConstruction exception = new IllegalConstruction(msg, cause);
        assertEquals(msg, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testToString() {
        String msg = "Error occurred";
        IllegalConstruction exception = new IllegalConstruction(msg);
        assertTrue(exception.toString().contains("IllegalConstruction: " + msg));
    }
}
