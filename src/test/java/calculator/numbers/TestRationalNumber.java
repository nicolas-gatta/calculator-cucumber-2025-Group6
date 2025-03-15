package calculator.numbers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestRationalNumber {

    @Test
    void testAddition() {
        RationalNumber r1 = new RationalNumber(1, 2); // 1/2
        RationalNumber r2 = new RationalNumber(1, 3); // 1/3
        RationalNumber result = r1.add(r2);
        assertEquals("5/6", result.toString());
    }

    @Test
    void testSubtraction() {
        RationalNumber r1 = new RationalNumber(3, 4); // 3/4
        RationalNumber r2 = new RationalNumber(1, 4); // 1/4
        RationalNumber result = r1.subtract(r2);
        assertEquals("1/2", result.toString());
    }

    @Test
    void testMultiplication() {
        RationalNumber r1 = new RationalNumber(2, 3); // 2/3
        RationalNumber r2 = new RationalNumber(3, 4); // 3/4
        RationalNumber result = r1.multiply(r2);
        assertEquals("1/2", result.toString());
    }

    @Test
    void testDivision() {
        RationalNumber r1 = new RationalNumber(1, 2); // 1/2
        RationalNumber r2 = new RationalNumber(1, 4); // 1/4
        RationalNumber result = r1.divide(r2);
        assertEquals("2/1", result.toString()); // Should be 2
    }

    @Test
    void testDivisionByZero() {
        RationalNumber r1 = new RationalNumber(1, 2); // 1/2
        RationalNumber r2 = new RationalNumber(0, 1); // 0/1
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            r1.divide(r2);
        });
        assertEquals("Cannot divide by zero.", exception.getMessage());
    }

    @Test
    void testSpecialValues() {
        // Test simplification
        RationalNumber r1 = new RationalNumber(6, 12); // 6/12
        assertEquals("1/2", r1.toString());

        // Test negative values
        RationalNumber r2 = new RationalNumber(-3, 4); // -3/4
        assertEquals("-3/4", r2.toString());

        // Test mixed numbers
        RationalNumber r3 = new RationalNumber(18, 12); // 18/12
        assertEquals("3/2", r3.toString()); // Should simplify to 3/2
    }
    
    @Test
    void testConstructorWithZeroDenominator() {
        // Test that constructor throws exception when denominator is zero
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RationalNumber(1, 0);
        });
        assertEquals("Denominator cannot be zero.", exception.getMessage());
    }
    
    @Test
    void testGCD() {
        // Test GCD functionality through constructor simplification
        
        // Test with positive numbers
        RationalNumber r1 = new RationalNumber(15, 25); // GCD = 5
        assertEquals(3, r1.getNumerator());
        assertEquals(5, r1.getDenominator());
        
        // Test with negative numerator
        RationalNumber r2 = new RationalNumber(-8, 12); // GCD = 4
        assertEquals(-2, r2.getNumerator());
        assertEquals(3, r2.getDenominator());
        
        // Test with negative denominator
        RationalNumber r3 = new RationalNumber(8, -12); // GCD = 4
        assertEquals(-2, r3.getNumerator());
        assertEquals(3, r3.getDenominator());
        
        // Test with both negative
        RationalNumber r4 = new RationalNumber(-8, -12); // GCD = 4
        assertEquals(2, r4.getNumerator());
        assertEquals(3, r4.getDenominator());
    }
    
    @Test
    void testGetters() {
        RationalNumber r = new RationalNumber(3, 4);
        assertEquals(3, r.getNumerator());
        assertEquals(4, r.getDenominator());
    }
    
    @Test
    void testEquals() {
        RationalNumber r1 = new RationalNumber(1, 2);
        RationalNumber r2 = new RationalNumber(1, 2);
        RationalNumber r3 = new RationalNumber(2, 4); // Simplifies to 1/2
        RationalNumber r4 = new RationalNumber(2, 3);
        
        // Test equality with same values
        assertEquals(r1, r2);
        
        // Test equality with simplified values
        assertEquals(r1, r3);
        
        // Test inequality with different values
        assertNotEquals(r1, r4);
        
        // Test equality with self
        assertEquals(r1, r1);
        
        // Test inequality with null
        assertNotEquals(r1, null);
        
        // Test inequality with different type
        assertNotEquals(r1, "1/2");
    }
    
    @Test
    void testHashCode() {
        RationalNumber r1 = new RationalNumber(1, 2);
        RationalNumber r2 = new RationalNumber(2, 4); // Simplifies to 1/2
        
        // Same values should have same hash code
        assertEquals(r1.hashCode(), r2.hashCode());
    }
    
    @Test
    void testToString() {
        // Test positive rational
        RationalNumber r1 = new RationalNumber(1, 2);
        assertEquals("1/2", r1.toString());
        
        // Test negative rational
        RationalNumber r2 = new RationalNumber(-1, 2);
        assertEquals("-1/2", r2.toString());
        
        // Test integer (denominator = 1)
        RationalNumber r3 = new RationalNumber(5, 1);
        assertEquals("5/1", r3.toString());
    }
    
    @Test
    void testOperationsWithNegativeNumbers() {
        // Addition with negative numbers
        RationalNumber r1 = new RationalNumber(-1, 3);
        RationalNumber r2 = new RationalNumber(1, 2);
        assertEquals("1/6", r1.add(r2).toString()); // -1/3 + 1/2 = 1/6
        
        // Subtraction with negative numbers
        RationalNumber r3 = new RationalNumber(1, 4);
        RationalNumber r4 = new RationalNumber(-1, 3);
        assertEquals("7/12", r3.subtract(r4).toString()); // 1/4 - (-1/3) = 1/4 + 1/3 = 7/12
        
        // Multiplication with negative numbers
        RationalNumber r5 = new RationalNumber(-2, 5);
        RationalNumber r6 = new RationalNumber(3, 7);
        assertEquals("-6/35", r5.multiply(r6).toString()); // (-2/5) * (3/7) = -6/35
        
        // Division with negative numbers
        RationalNumber r7 = new RationalNumber(-1, 2);
        RationalNumber r8 = new RationalNumber(1, 4);
        assertEquals("-2/1", r7.divide(r8).toString()); // (-1/2) / (1/4) = -2
    }

} 