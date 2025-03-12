package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.numbers.MyNumber;
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
    private int value1, value2, value3;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
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

}
