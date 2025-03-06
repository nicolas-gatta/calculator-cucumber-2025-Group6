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
        assertEquals(6.0, ((Number)calc.eval(plus)).doubleValue(), DELTA);
    }

    @Test
    void testMinusWithReals() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(
            new RealNumber(5.5, 2),
            new RealNumber(2.2, 2)
        );
        Minus minus = new Minus(params);
        Calculator calc = new Calculator();
        assertEquals(3.3, ((Number)calc.eval(minus)).doubleValue(), DELTA);
    }

    @Test
    void testTimesWithReals() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(
            new RealNumber(2.5, 2),
            new RealNumber(3.0, 2)
        );
        Times times = new Times(params);
        Calculator calc = new Calculator();
        assertEquals(7.5, ((Number)calc.eval(times)).doubleValue(), DELTA);
    }

    @Test
    void testDividesWithReals() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(
            new RealNumber(10.0, 2),
            new RealNumber(2.0, 2)
        );
        Divides divides = new Divides(params);
        Calculator calc = new Calculator();
        assertEquals(5.0, ((Number)calc.eval(divides)).doubleValue(), DELTA);
    }

    @Test
    void testMixedOperations() throws IllegalConstruction {
        // Test (3.14 + 2) * 2.0
        List<Expression> addParams = Arrays.asList(
            new RealNumber(3.14, 2),
            new MyNumber(2)
        );
        Plus plus = new Plus(addParams);
        
        List<Expression> timesParams = Arrays.asList(
            plus,
            new RealNumber(2.0, 2)
        );
        Times times = new Times(timesParams);
        
        Calculator calc = new Calculator();
        assertEquals(10.28, ((Number)calc.eval(times)).doubleValue(), DELTA);
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
        
        assertEquals(Double.POSITIVE_INFINITY, 
            ((Number)calc.eval(divides)).doubleValue(), DELTA);
    }
} 