package calculator;

import calculator.numbers.MyNumber;
import calculator.numbers.RealNumber;
import calculator.operations.Divides;
import calculator.operations.Minus;
import calculator.operations.Plus;
import calculator.operations.Times;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class TestRealOperations {
    private static final double DELTA = 1e-10;

    @Test
    void testPlusWithReals() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(
            new RealNumber(3.14, 2),
            new RealNumber(2.86, 2)
        );
        Plus plus = new Plus(params);
        Calculator calc = new Calculator();

        Expression result = calc.eval(plus);
        assertTrue(result instanceof RealNumber);
        RealNumber realResult = (RealNumber) result;
        assertEquals(6.0, realResult.getValue(), DELTA);
    }

    @Test
    void testMinusWithReals() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(
            new RealNumber(5.5, 2),
            new RealNumber(2.2, 2)
        );
        Minus minus = new Minus(params);
        Calculator calc = new Calculator();
        
        Expression result = calc.eval(minus);
        
        assertTrue(result instanceof RealNumber);
        RealNumber realResult = (RealNumber) result;
        assertEquals(3.3, realResult.getValue(), DELTA);
    }

    @Test
    void testTimesWithReals() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(
            new RealNumber(2.5, 2),
            new RealNumber(3.0, 2)
        );
        Times times = new Times(params);
        Calculator calc = new Calculator();
        
        Expression result = calc.eval(times);
        
        assertTrue(result instanceof RealNumber);
        RealNumber realResult = (RealNumber) result;
        assertEquals(7.5, realResult.getValue(), DELTA);
    
    }

    @Test
    void testDividesWithReals() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(
            new RealNumber(10.0, 2),
            new RealNumber(2.0, 2)
        );
        Divides divides = new Divides(params);
        Calculator calc = new Calculator();
        
        Expression result = calc.eval(divides);
        
        assertTrue(result instanceof RealNumber);
        RealNumber realResult = (RealNumber) result;
        assertEquals(5.0, realResult.getValue(), DELTA);
    }

    @Test
    void testDivisionByZero() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(
            new RealNumber(5.0, 2),
            new RealNumber(0.0, 2)
        );
        Divides divides = new Divides(params);
        Calculator calc = new Calculator();
        
        assertNull(calc.eval(divides));
    }    

    @Test
    void testDivisionWithInfinity() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(
            new RealNumber(Double.POSITIVE_INFINITY, 2),
            new RealNumber(2.0, 2)
        );
        Divides divides = new Divides(params);
        Calculator calc = new Calculator();
        
        Expression result = calc.eval(divides);
        
        assertTrue(result instanceof RealNumber);
        RealNumber realResult = (RealNumber) result;
        assertEquals(Double.POSITIVE_INFINITY, realResult.getValue(), DELTA);
    }
    
} 