package calculator.gui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;

/**
 * Test class for the NumberType enum.
 */
@Tag("gui")
public class NumberTypeTest {

    /**
     * Tests that each NumberType has the correct display name.
     */
    @Test
    public void testDisplayNames() {
        assertEquals("Integer", NumberType.INTEGER.toString());
        assertEquals("Rational", NumberType.RATIONAL.toString());
        assertEquals("Real", NumberType.REAL.toString());
        assertEquals("Complex", NumberType.COMPLEX.toString());
    }
    
    /**
     * Tests that all expected number types are defined.
     */
    @Test
    public void testEnumValues() {
        NumberType[] types = NumberType.values();
        assertEquals(7, types.length);
        
        // Verify all expected types exist
        boolean hasInteger = false;
        boolean hasRational = false;
        boolean hasReal = false;
        boolean hasComplex = false;
        
        for (NumberType type : types) {
            switch (type) {
                case INTEGER:
                    hasInteger = true;
                    break;
                case RATIONAL:
                    hasRational = true;
                    break;
                case REAL:
                    hasReal = true;
                    break;
                case COMPLEX:
                    hasComplex = true;
                    break;
            }
        }
        
        assertTrue(hasInteger, "INTEGER type should exist");
        assertTrue(hasRational, "RATIONAL type should exist");
        assertTrue(hasReal, "REAL type should exist");
        assertTrue(hasComplex, "COMPLEX type should exist");
    }
    
    /**
     * Tests the valueOf method for the enum.
     */
    @Test
    public void testValueOf() {
        assertEquals(NumberType.INTEGER, NumberType.valueOf("INTEGER"));
        assertEquals(NumberType.RATIONAL, NumberType.valueOf("RATIONAL"));
        assertEquals(NumberType.REAL, NumberType.valueOf("REAL"));
        assertEquals(NumberType.COMPLEX, NumberType.valueOf("COMPLEX"));
    }
    
    /**
     * Tests that invalid enum values throw the expected exception.
     */
    @Test
    public void testInvalidValueOf() {
        assertThrows(IllegalArgumentException.class, () -> NumberType.valueOf("INVALID_TYPE"));
    }
} 