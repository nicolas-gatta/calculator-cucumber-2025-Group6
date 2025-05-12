package visitor;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.Calculator;
import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.matrix.Matrix;
import calculator.matrix.MatrixExpression;
import calculator.numbers.MyNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.RationalNumber;
import calculator.operations.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

class TestEvaluator {

    private Calculator calc;
    private Evaluator evaluator;
    private int value1, value2, value3;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        evaluator = new Evaluator();
        value1 = 8;
        value2 = 6;
        value3 = 0;
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-"})
    void testEvaluateOperations(String symbol) {
        List<Expression> params = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
        try {
            Expression result = null;
            switch (symbol) {
                case "+":
                    result = calc.eval(new Plus(params));
                    break;
                case "-":
                    result = calc.eval(new Minus(params));
                    break;
                case "*":
                    result = calc.eval(new Times(params));
                    break;
                case "/":
                    result = calc.eval(new Divides(params));
                    break;
                default:
                    fail();
            }
            
            assertTrue(result instanceof MyNumber);
            int expectedValue = 0;
            switch (symbol) {
                case "+":
                    expectedValue = value1 + value2;
                    break;
                case "-":
                    expectedValue = value1 - value2;
                    break;
                case "*":
                    expectedValue = value1 * value2;
                    break;
                case "/":
                    if (value2 != 0) {
                        expectedValue = value1 / value2;
                    } else {
                        expectedValue = 0; // Handle divide by zero case
                    }
                    break;
            }
    
            assertEquals(expectedValue, ((MyNumber) result).getValue());
            
        } catch (IllegalConstruction e) {
            fail();
        }
    }
    
    @Test
    void testDivideByZero() {
        List<Expression> params = Arrays.asList(new MyNumber(value1),new MyNumber(value3));
        try {
            assertNull(calc.eval(new Divides(params)));
        } catch (IllegalConstruction e) {
            fail();
        }
    }
    
    @Test
    void testEvaluatorDirectly() {
        // Test evaluator directly without using Calculator
        MyNumber n = new MyNumber(42);
        n.accept(evaluator);
        Expression result = evaluator.getResult();
        assertEquals(n, result);
    }
    
    @Test
    void testEvaluateRealNumbers() {
        try {
            // Test addition of RealNumbers
            RealNumber r1 = new RealNumber(3.5, 2);
            RealNumber r2 = new RealNumber(1.5, 2);
            List<Expression> params = Arrays.asList(r1, r2);
            
            Plus plus = new Plus(params);
            plus.accept(evaluator);
            Expression result = evaluator.getResult();
            
            assertTrue(result instanceof RealNumber);
            assertEquals(5.0, ((RealNumber) result).getValue(), 0.001);
            
            // Test subtraction of RealNumbers
            Minus minus = new Minus(params);
            minus.accept(evaluator);
            result = evaluator.getResult();
            
            assertTrue(result instanceof RealNumber);
            assertEquals(2.0, ((RealNumber) result).getValue(), 0.001);
            
            // Test multiplication of RealNumbers
            Times times = new Times(params);
            times.accept(evaluator);
            result = evaluator.getResult();
            
            assertTrue(result instanceof RealNumber);
            assertEquals(5.25, ((RealNumber) result).getValue(), 0.001);
            
            // Test division of RealNumbers
            Divides divides = new Divides(params);
            divides.accept(evaluator);
            result = evaluator.getResult();
            
            assertTrue(result instanceof RealNumber);
            assertEquals(2.33, ((RealNumber) result).getValue(), 0.01);
        } catch (IllegalConstruction e) {
            fail();
        }
    }
    
    @Test
    void testEvaluateComplexNumbers() {
        try {
            // Test addition of ComplexNumbers
            ComplexNumber c1 = new ComplexNumber(3, 4);
            ComplexNumber c2 = new ComplexNumber(1, 2);
            List<Expression> params = Arrays.asList(c1, c2);
            
            Plus plus = new Plus(params);
            plus.accept(evaluator);
            Expression result = evaluator.getResult();
            
            assertTrue(result instanceof ComplexNumber);
            assertEquals(4.0, ((ComplexNumber) result).getReal(), 0.001);
            assertEquals(6.0, ((ComplexNumber) result).getImaginary(), 0.001);
            
            // Test subtraction of ComplexNumbers
            Minus minus = new Minus(params);
            minus.accept(evaluator);
            result = evaluator.getResult();
            
            assertTrue(result instanceof ComplexNumber);
            assertEquals(2.0, ((ComplexNumber) result).getReal(), 0.001);
            assertEquals(2.0, ((ComplexNumber) result).getImaginary(), 0.001);
        } catch (IllegalConstruction e) {
            fail();
        }
    }
    
    @Test
    void testEvaluateRationalNumbers() {
        try {
            // Test addition of RationalNumbers
            RationalNumber r1 = new RationalNumber(1, 2);
            RationalNumber r2 = new RationalNumber(1, 3);
            List<Expression> params = Arrays.asList(r1, r2);
            
            Plus plus = new Plus(params);
            plus.accept(evaluator);
            Expression result = evaluator.getResult();
            
            assertTrue(result instanceof RationalNumber);
            assertEquals(5, ((RationalNumber) result).getNumerator());
            assertEquals(6, ((RationalNumber) result).getDenominator());
        } catch (IllegalConstruction e) {
            fail();
        }
    }
    
    @Test
    void testEvaluateNestedExpressions() {
        try {
            // Create a nested expression: (5 + 3) * (10 - 2)
            Expression e1 = new Plus(Arrays.asList(new MyNumber(5), new MyNumber(3)));
            Expression e2 = new Minus(Arrays.asList(new MyNumber(10), new MyNumber(2)));
            Expression complex = new Times(Arrays.asList(e1, e2));
            
            complex.accept(evaluator);
            Expression result = evaluator.getResult();
            
            assertTrue(result instanceof MyNumber);
            assertEquals(64, ((MyNumber) result).getValue());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testMyNumberPlusRealNumber() throws IllegalConstruction {
        MyNumber a = new MyNumber(2);
        RealNumber b = new RealNumber(3.5);
        Plus plus = new Plus(List.of(a, b));
        plus.accept(evaluator);

        Expression result = evaluator.getResult();
        assertInstanceOf(RealNumber.class, result);
        assertEquals(5.5, ((RealNumber) result).getValue(), 0.0001);
    }

    @Test
    void testMyNumberMinusRationalNumber() throws IllegalConstruction {
        MyNumber a = new MyNumber(5);
        RationalNumber b = new RationalNumber(9,4);
        Minus minus = new Minus(List.of(a, b));
        minus.accept(evaluator);
        Expression result = evaluator.getResult();
        assertInstanceOf(RealNumber.class, result);
        assertEquals(2.75, ((RealNumber) result).getValue(), 0.0001);
    }

    @Test
    void testRationalTimesMyNumber() throws IllegalConstruction {
        RationalNumber a = new RationalNumber(2, 3);
        MyNumber b = new MyNumber(6);
        Times times = new Times(List.of(a, b));
        times.accept(evaluator);

        Expression result = evaluator.getResult();
        assertInstanceOf(RealNumber.class, result);
        assertEquals(4.0, ((RealNumber) result).getValue(), 0.0001);
    }

    @Test
    void testRealNumberDividesMyNumber() throws IllegalConstruction {
        RealNumber a = new RealNumber(6);
        MyNumber b = new MyNumber(2);
        Divides div = new Divides(List.of(a, b));
        div.accept(evaluator);
        Expression result = evaluator.getResult();
        assertInstanceOf(RealNumber.class, result);
        assertEquals(3.0, ((RealNumber) result).getValue(), 0.0001);
    }

    @Test
    void testRealNumberPlusRationalNumber() throws IllegalConstruction {
        RealNumber a = new RealNumber(2.5);
        RationalNumber b = new RationalNumber(1, 2);
        Plus plus = new Plus(List.of(a, b));
        plus.accept(evaluator);
        Expression result = evaluator.getResult();
        assertInstanceOf(RealNumber.class, result);
        assertEquals(3.0, ((RealNumber) result).getValue(), 0.0001);
    }

    @Test
    void testRationalPlusRealNumber() throws IllegalConstruction {
        RationalNumber a = new RationalNumber(3, 4);
        RealNumber b = new RealNumber(2);
        Plus plus = new Plus(List.of(a, b));
        plus.accept(evaluator);
        Expression result = evaluator.getResult();
        assertInstanceOf(RealNumber.class, result);
        assertEquals(2.75, ((RealNumber) result).getValue(), 0.0001);
    }

    @Test
    void testRealNumberDivideByZeroRational() throws IllegalConstruction {
        RealNumber n1 = new RealNumber(3.14);
        RationalNumber zero = new RationalNumber(0, 1);
        Divides div = new Divides(List.of(n1, zero));
        div.accept(evaluator);
        assertNull(evaluator.getResult());
    }




//    @Test
//    void testMatrixTranspose() throws IllegalConstruction {
//        double[][] values = {{1, 2}, {3, 4}};
//        Matrix matrix = new Matrix(values);
//        MatrixExpression m = new MatrixExpression(matrix);
//
//        MatrixTranspose transpose = new MatrixTranspose(List.of(m));
//        transpose.accept(evaluator);
//        MatrixExpression result = (MatrixExpression) evaluator.getResult();
//
//        double[][] expected = {{1, 3}, {2, 4}};
//        Matrix expectedMatrix = new Matrix(expected);
//        MatrixExpression mExpected = new MatrixExpression(expectedMatrix);
//        assertEquals(mExpected, result);
//    }
//
    @Test
    void testRealNumberDivideByZero() throws IllegalConstruction {
        RealNumber n1 = new RealNumber(3.14);
        RealNumber zero = new RealNumber(0);
        Divides div = new Divides(List.of(n1, zero));
        div.accept(evaluator);
        assertNull(evaluator.getResult());
    }

//    @Test
//    void testMatrixTimesScalar() throws IllegalConstruction {
//        double[][] data = {{1, 2}, {3, 4}};
//        Matrix matrix = new Matrix(data);
//        MatrixExpression m = new MatrixExpression(matrix);
//        RealNumber scalar = new RealNumber(2);
//
//        Times times = new Times(List.of(m, scalar));
//        times.accept(evaluator);
//
//        double[][] expected = {{2, 4}, {6, 8}};
//        Matrix expectedMatrix = new Matrix(expected);
//        assertArrayEquals(expected,  evaluator.getResult().getMatrix());
//    }
}
