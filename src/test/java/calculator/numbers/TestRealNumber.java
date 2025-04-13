package calculator.numbers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestRealNumber {
    
    @Test
    void testRealNumberCreation() {
        RealNumber n = new RealNumber(3.14159, 5);
        assertEquals(3.14159, n.getValue(), 0.00001);
        assertEquals(5, n.getPrecision());
    }

    @Test
    void testPrecisionChange() {
        RealNumber n = new RealNumber(3.14159, 2);
        assertEquals("3,14", n.toString());
        n.setPrecision(4);
        assertEquals("3,1416", n.toString());
    }

    @Test
    void testLargeNumber() {
        RealNumber n = new RealNumber(6.022e23, 4);
        assertEquals(6.022e23, n.getValue(), 1e18);
    }

    @Test
    void testSmallNumber() {
        RealNumber n = new RealNumber(1.6e-35, 2);
        assertEquals(1.6e-35, n.getValue(), 1e-36);
    }

    @Test
    void testInvalidPrecision() {
        // Test negative precision
        assertThrows(IllegalArgumentException.class, 
            () -> new RealNumber(3.14, -1));
        
        // Test null precision
        assertThrows(IllegalArgumentException.class, 
            () -> new RealNumber(3.14, 0));
    }

    @Test
    void testSpecialValues() {
        // Test Infinity
        RealNumber posInf = new RealNumber(Double.POSITIVE_INFINITY, 2);
        assertEquals(Double.POSITIVE_INFINITY, posInf.getValue());
        
        RealNumber negInf = new RealNumber(Double.NEGATIVE_INFINITY, 2);
        assertEquals(Double.NEGATIVE_INFINITY, negInf.getValue());
        
        // Test NaN
        RealNumber nan = new RealNumber(Double.NaN, 2);
        assertTrue(Double.isNaN(nan.getValue()));
    }
} 