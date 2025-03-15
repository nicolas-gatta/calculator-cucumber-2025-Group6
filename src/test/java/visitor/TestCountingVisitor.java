package visitor;

import calculator.*;
import calculator.numbers.MyNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.RationalNumber;
import calculator.operations.Minus;
import calculator.operations.Operation;
import calculator.operations.Plus;
import calculator.operations.Times;
import calculator.operations.Divides;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

/**
 * Test class for CountingVisitor
 * Tests the counting of depth, operations and numbers in expressions
 */
class TestCountingVisitor {
    
    private CountingVisitor visitor;
    
    @BeforeEach
    void setUp() {
        visitor = new CountingVisitor();
    }

    /**
     * Test that a single number has:
     * - depth of 0 (no nested expressions)
     * - 0 operations
     * - 1 number
     */
    @Test
    void testNumberCounting() {
        MyNumber number = new MyNumber(42);
        visitor.visit(number);
        
        assertEquals(0, visitor.getDepth());
        assertEquals(0, visitor.getOperations());
        assertEquals(1, visitor.getNumbers());
    }
    
    /**
     * Test that a real number has:
     * - depth of 0 (no nested expressions)
     * - 0 operations
     * - 1 number
     */
    @Test
    void testRealNumberCounting() {
        RealNumber number = new RealNumber(3.14, 2);
        visitor.visit(number);
        
        assertEquals(0, visitor.getDepth());
        assertEquals(0, visitor.getOperations());
        assertEquals(1, visitor.getNumbers());
    }
    
    /**
     * Test that a complex number has:
     * - depth of 0 (no nested expressions)
     * - 0 operations
     * - 1 number
     */
    @Test
    void testComplexNumberCounting() {
        ComplexNumber number = new ComplexNumber(3, 4);
        visitor.visit(number);
        
        assertEquals(0, visitor.getDepth());
        assertEquals(0, visitor.getOperations());
        assertEquals(1, visitor.getNumbers());
    }
    
    /**
     * Test that a rational number has:
     * - depth of 0 (no nested expressions)
     * - 0 operations
     * - 1 number
     */
    @Test
    void testRationalNumberCounting() {
        RationalNumber number = new RationalNumber(1, 2);
        visitor.visit(number);
        
        assertEquals(0, visitor.getDepth());
        assertEquals(0, visitor.getOperations());
        assertEquals(1, visitor.getNumbers());
    }

    /**
     * Test that a simple operation (e.g., 5 + 3) has:
     * - depth of 1 (one level of operation)
     * - 1 operation
     * - 2 numbers
     */
    @Test
    void testSimpleOperationCounting() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(new MyNumber(5), new MyNumber(3));
        Operation op = new Plus(params);
        op.accept(visitor);
        
        assertEquals(1, visitor.getDepth());
        assertEquals(1, visitor.getOperations());
        assertEquals(2, visitor.getNumbers());
    }
    
    /**
     * Test that an operation with different number types has:
     * - depth of 1 (one level of operation)
     * - 1 operation
     * - 2 numbers
     */
    @Test
    void testMixedTypeOperationCounting() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(
            new MyNumber(5), 
            new RealNumber(3.14, 2)
        );
        Operation op = new Plus(params);
        op.accept(visitor);
        
        assertEquals(1, visitor.getDepth());
        assertEquals(1, visitor.getOperations());
        assertEquals(2, visitor.getNumbers());
    }
    
    /**
     * Test that an operation with three arguments has:
     * - depth of 1 (one level of operation)
     * - 1 operation
     * - 3 numbers
     */
    @Test
    void testThreeArgumentOperationCounting() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(
            new MyNumber(5), 
            new MyNumber(3),
            new MyNumber(7)
        );
        Operation op = new Plus(params);
        op.accept(visitor);
        
        assertEquals(1, visitor.getDepth());
        assertEquals(1, visitor.getOperations());
        assertEquals(3, visitor.getNumbers());
    }

    /**
     * Test that a complex expression ((5 + 3) * (2 - 1)) has:
     * - depth of 2 (nested operations)
     * - 3 operations (plus, minus, times)
     * - 4 numbers (5,3,2,1)
     */
    @Test
    void testComplexExpressionCounting() throws IllegalConstruction {
        // Create expression: (5 + 3) * (2 - 1)
        List<Expression> params1 = Arrays.asList(new MyNumber(5), new MyNumber(3));
        Operation plus = new Plus(params1);
        List<Expression> params2 = Arrays.asList(new MyNumber(2), new MyNumber(1));
        Operation minus = new Minus(params2);
        List<Expression> params3 = Arrays.asList(plus, minus);
        Operation times = new Times(params3);

        times.accept(visitor);
        
        assertEquals(2, visitor.getDepth());
        assertEquals(3, visitor.getOperations());
        assertEquals(4, visitor.getNumbers());
    }
    
    /**
     * Test a very deep nested expression:
     * (((1+2)*(3-4))/(5+6))
     */
    @Test
    void testDeeplyNestedExpressionCounting() throws IllegalConstruction {
        // Create expression: (((1+2)*(3-4))/(5+6))
        List<Expression> params1 = Arrays.asList(new MyNumber(1), new MyNumber(2));
        Operation plus1 = new Plus(params1);
        
        List<Expression> params2 = Arrays.asList(new MyNumber(3), new MyNumber(4));
        Operation minus = new Minus(params2);
        
        List<Expression> params3 = Arrays.asList(plus1, minus);
        Operation times = new Times(params3);
        
        List<Expression> params4 = Arrays.asList(new MyNumber(5), new MyNumber(6));
        Operation plus2 = new Plus(params4);
        
        List<Expression> params5 = Arrays.asList(times, plus2);
        Operation divides = new Divides(params5);

        divides.accept(visitor);
        
        assertEquals(3, visitor.getDepth());
        assertEquals(5, visitor.getOperations());
        assertEquals(6, visitor.getNumbers());
    }
    
    /**
     * Test that the visitor correctly resets its state between visits
     */
    @Test
    void testVisitorReset() throws IllegalConstruction {
        // First visit a simple expression
        List<Expression> params1 = Arrays.asList(new MyNumber(5), new MyNumber(3));
        Operation plus = new Plus(params1);
        plus.accept(visitor);
        
        assertEquals(1, visitor.getDepth());
        assertEquals(1, visitor.getOperations());
        assertEquals(2, visitor.getNumbers());
        
        // Then visit a single number
        MyNumber number = new MyNumber(42);
        number.accept(visitor);
        
        // Verify that the counts have been reset
        assertEquals(0, visitor.getDepth());
        assertEquals(0, visitor.getOperations());
        assertEquals(1, visitor.getNumbers());
    }
} 