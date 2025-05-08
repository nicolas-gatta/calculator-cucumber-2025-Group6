package calculator.numbers;

import calculator.Calculator;
import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;
import calculator.operations.Plus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestComplexNumber {

    Expression e;
    Calculator c = new Calculator();

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

    @Test
    void testPrefixNotation() throws IllegalConstruction {
        ComplexNumber c1 = new ComplexNumber(3, 4); // 3 + 4i
        ComplexNumber c2 = new ComplexNumber(1, 2); // 1 + 2i

        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, c1, c2);
        e = new Plus(params, Notation.PREFIX);

        assertEquals("+ (3.0 + 4.0i, 1.0 + 2.0i)", e.toString());
    }

    @Test
    void testInfixNotation() throws IllegalConstruction {
        ComplexNumber c1 = new ComplexNumber(3, 4); // 3 + 4i
        ComplexNumber c2 = new ComplexNumber(1, 2); // 1 + 2i

        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, c1, c2);
        e = new Plus(params, Notation.INFIX);

        assertEquals("(3.0 + 4.0i + 1.0 + 2.0i)", e.toString());
    }

    @Test
    void testPostfixNotation() throws IllegalConstruction {
        ComplexNumber c1 = new ComplexNumber(3, 4); // 3 + 4i
        ComplexNumber c2 = new ComplexNumber(1, 2); // 1 + 2i

        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, c1, c2);
        e = new Plus(params, Notation.POSTFIX);

        assertEquals("(3.0 + 4.0i, 1.0 + 2.0i) +", e.toString());
    }




} 