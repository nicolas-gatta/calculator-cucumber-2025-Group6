package calculator;

import calculator.numbers.ComplexNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestComplexNumber {

    @Test
    void testAddition() {
        ComplexNumber c1 = new ComplexNumber(3, 4); // 3 + 4i
        ComplexNumber c2 = new ComplexNumber(1, 2); // 1 + 2i
        ComplexNumber result = c1.add(c2);
        assertEquals("4.0 + 6.0i", result.toString());
    }

    @Test
    void testSubtraction() {
        ComplexNumber c1 = new ComplexNumber(5, 7); // 5 + 7i
        ComplexNumber c2 = new ComplexNumber(2, 3); // 2 + 3i
        ComplexNumber result = c1.subtract(c2);
        assertEquals("3.0 + 4.0i", result.toString());
    }

    @Test
    void testMultiplication() {
        ComplexNumber c1 = new ComplexNumber(2, 3); // 2 + 3i
        ComplexNumber c2 = new ComplexNumber(4, 5); // 4 + 5i
        ComplexNumber result = c1.multiply(c2);
        assertEquals("-7.0 + 22.0i", result.toString());
    }

    @Test
    void testDivision() {
        ComplexNumber c1 = new ComplexNumber(3, 2); // 3 + 2i
        ComplexNumber c2 = new ComplexNumber(1, 1); // 1 + 1i
        ComplexNumber result = c1.divide(c2);
        assertEquals("2.5 - 0.5i", result.toString());
    }

    @Test
    void testDivisionByZero() {
        ComplexNumber c1 = new ComplexNumber(1, 2); // 1 + 2i
        ComplexNumber c2 = new ComplexNumber(0, 0); // 0 + 0i
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            c1.divide(c2);
        });
        assertEquals("Cannot divide by zero.", exception.getMessage());
    }

    @Test
    void testAdditionWithZero() {
        ComplexNumber c1 = new ComplexNumber(3, 4); // 3 + 4i
        ComplexNumber zero = new ComplexNumber(0, 0); // 0 + 0i
        ComplexNumber result = c1.add(zero);
        assertEquals("3.0 + 4.0i", result.toString());
    }

    @Test
    void testSubtractionWithZero() {
        ComplexNumber c1 = new ComplexNumber(3, 4); // 3 + 4i
        ComplexNumber zero = new ComplexNumber(0, 0); // 0 + 0i
        ComplexNumber result = c1.subtract(zero);
        assertEquals("3.0 + 4.0i", result.toString());
    }

    @Test
    void testMultiplicationWithZero() {
        ComplexNumber c1 = new ComplexNumber(3, 4); // 3 + 4i
        ComplexNumber zero = new ComplexNumber(0, 0); // 0 + 0i
        ComplexNumber result = c1.multiply(zero);
        assertEquals("0.0 + 0.0i", result.toString());
    }

    @Test
    void testDivisionWithZeroNumerator() {
        ComplexNumber zero = new ComplexNumber(0, 0); // 0 + 0i
        ComplexNumber c2 = new ComplexNumber(1, 1); // 1 + 1i
        ComplexNumber result = zero.divide(c2);
        assertEquals("0.0 + 0.0i", result.toString());
    }

    @Test
    void testCommutativity() {
        ComplexNumber c1 = new ComplexNumber(2, 3); // 2 + 3i
        ComplexNumber c2 = new ComplexNumber(4, 5); // 4 + 5i
        ComplexNumber sum1 = c1.add(c2);
        ComplexNumber sum2 = c2.add(c1);
        assertEquals(sum1.toString(), sum2.toString());
    }

    @Test
    void testAssociativity() {
        ComplexNumber c1 = new ComplexNumber(1, 2); // 1 + 2i
        ComplexNumber c2 = new ComplexNumber(3, 4); // 3 + 4i
        ComplexNumber c3 = new ComplexNumber(5, 6); // 5 + 6i
        ComplexNumber sum1 = c1.add(c2).add(c3);
        ComplexNumber sum2 = c1.add(c2.add(c3));
        assertEquals(sum1.toString(), sum2.toString());
    }
} 