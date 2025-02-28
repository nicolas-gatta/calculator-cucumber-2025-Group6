package visitor;

import calculator.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class TestCountingVisitor {

    @Test
    void testNumberCounting() {
        MyNumber number = new MyNumber(42);
        CountingVisitor visitor = new CountingVisitor();
        number.accept(visitor);
        
        assertEquals(0, visitor.getDepth());
        assertEquals(0, visitor.getOperations());
        assertEquals(1, visitor.getNumbers());
    }

    @Test
    void testSimpleOperationCounting() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(new MyNumber(5), new MyNumber(3));
        Operation op = new Plus(params);
        CountingVisitor visitor = new CountingVisitor();
        op.accept(visitor);
        
        assertEquals(1, visitor.getDepth());
        assertEquals(1, visitor.getOperations());
        assertEquals(2, visitor.getNumbers());
    }

    @Test
    void testComplexExpressionCounting() throws IllegalConstruction {
        // Create expression: (5 + 3) * (2 - 1)
        List<Expression> params1 = Arrays.asList(new MyNumber(5), new MyNumber(3));
        Operation plus = new Plus(params1);
        List<Expression> params2 = Arrays.asList(new MyNumber(2), new MyNumber(1));
        Operation minus = new Minus(params2);
        List<Expression> params3 = Arrays.asList(plus, minus);
        Operation times = new Times(params3);

        CountingVisitor visitor = new CountingVisitor();
        times.accept(visitor);
        
        assertEquals(2, visitor.getDepth());
        assertEquals(3, visitor.getOperations());
        assertEquals(4, visitor.getNumbers());
    }
} 