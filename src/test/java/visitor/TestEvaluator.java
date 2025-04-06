package visitor;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.Calculator;
import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.numbers.MyNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.RationalNumber;
import calculator.operations.Divides;
import calculator.operations.Minus;
import calculator.operations.Plus;
import calculator.operations.Times;
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
}
