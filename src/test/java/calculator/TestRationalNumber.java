package calculator;

import calculator.numbers.RationalNumber;
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
} 