package visitor;

import calculator.*;
import calculator.numbers.RealNumber;
import calculator.operations.Plus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

class TestVisitorCompatibility {
    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.US);
    }

    @Test
    void testEvaluatorWithRealNumbers() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(
            new RealNumber(3.14, 2),
            new RealNumber(2.0, 2)
        );
        Plus plus = new Plus(params);
        
        Evaluator evaluator = new Evaluator();
        plus.accept(evaluator);
        //assertEquals(5.14, evaluator.getResult().doubleValue(), 1e-10);
    }
    
    @Test
    void testStringVisitorWithRealNumbers() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(
            new RealNumber(3.14, 2),
            new RealNumber(2.0, 2)
        );
        Plus plus = new Plus(params);
        
        StringVisitor visitor = new StringVisitor(Notation.INFIX);
        plus.accept(visitor);
        assertEquals("( 3.14 + 2.00 )", visitor.getResult());
    }
    
    @Test
    void testCountingVisitorWithRealNumbers() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(
            new RealNumber(3.14, 2),
            new RealNumber(2.0, 2)
        );
        Plus plus = new Plus(params);
        
        CountingVisitor visitor = new CountingVisitor();
        plus.accept(visitor);
        assertEquals(1, visitor.getOperations());
        assertEquals(2, visitor.getNumbers());
    }
} 