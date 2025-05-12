package visitor;

import calculator.*;
import calculator.linear.Equation;
import calculator.linear.EquationExpression;
import calculator.linear.LinearEquationSystemExpression;
import calculator.linear.VariableExpression;
import calculator.numbers.ComplexNumber;
import calculator.numbers.MyNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.operations.Minus;
import calculator.operations.Operation;
import calculator.operations.Plus;
import calculator.operations.Times;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

/**
 * Test class for StringVisitor
 * Tests the string representation of expressions in different notations
 */
class TestStringVisitor {

    /**
     * Test that a number is correctly converted to its string representation
     */
    @Test
    void testNumberToString() {
        MyNumber number = new MyNumber(42);
        StringVisitor visitor = new StringVisitor(Notation.INFIX);
        number.accept(visitor);
        assertEquals("42", visitor.getResult());
    }

    @Test
    void testRationalNumberToString() {
        RationalNumber number = new RationalNumber(42,2);
        StringVisitor visitor = new StringVisitor(Notation.INFIX);
        number.accept(visitor);
        assertEquals("21/1", visitor.getResult());
    }

    @Test
    void testEquationToString() throws IllegalConstruction {
        EquationExpression equation = new EquationExpression(new Equation(new Plus(List.of(new VariableExpression(new MyNumber(50),"y"),new VariableExpression(new MyNumber(50),"x"))),new MyNumber(5)));
        StringVisitor visitor = new StringVisitor(Notation.INFIX);
        equation.accept(visitor);
        assertEquals("(50.0000y + 50.0000x) = 5", visitor.getResult());
    }

    @Test
    void testLinearEquationToString() throws IllegalConstruction{
        EquationExpression eq1 = new EquationExpression(new Equation(
                new Minus(List.of(new VariableExpression("x"), new Plus(List.of(new VariableExpression(new MyNumber(-1),"y"), new VariableExpression(new MyNumber(-1),"z"))))),
                new MyNumber(6))
        );

        EquationExpression eq2 = new EquationExpression(new Equation(
                new Plus(List.of(new VariableExpression(new MyNumber(2), "x"), new VariableExpression(new MyNumber(3), "y"))),
                new MyNumber(0)));

        EquationExpression eq3 = new EquationExpression(new Equation(
                new Plus(List.of(new VariableExpression(new MyNumber(3), "x"),
                        new Minus(List.of(new VariableExpression(new RationalNumber(8,2), "y"), new VariableExpression(new MyNumber(10), "z"))))),
                new MyNumber(-6)
        ));

        LinearEquationSystemExpression system = new LinearEquationSystemExpression(List.of(eq1, eq2, eq3));

        StringVisitor visitor = new StringVisitor(Notation.INFIX);

        system.accept(visitor);

        assertEquals("((x - (-1.0000y + -1.0000z)) = 6, (2.0000x + 3.0000y) = 0, (3.0000x + (4.0000y - 10.0000z)) = -6)", visitor.getResult());
    }

    /**
     * Test that an operation is correctly converted to infix notation
     * Example: "( 5 + 3 )"
     */
    @Test
    void testOperationToStringInfix() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(new MyNumber(5), new MyNumber(3));
        Operation op = new Plus(params);
        StringVisitor visitor = new StringVisitor(Notation.INFIX);
        op.accept(visitor);
        assertEquals("(5 + 3)", visitor.getResult());
    }

    /**
     * Test that an operation is correctly converted to prefix notation
     * Example: "+ (5, 3)"
     */
    @Test
    void testOperationToStringPrefix() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(new MyNumber(5), new MyNumber(3));
        Operation op = new Plus(params);
        StringVisitor visitor = new StringVisitor(Notation.PREFIX);
        op.accept(visitor);
        assertEquals("+ (5, 3)", visitor.getResult());
    }

    /**
     * Test that an operation is correctly converted to postfix notation
     * Example: "(5, 3) +"
     */
    @Test
    void testOperationToStringPostfix() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(new MyNumber(5), new MyNumber(3));
        Operation op = new Plus(params);
        StringVisitor visitor = new StringVisitor(Notation.POSTFIX);
        op.accept(visitor);
        assertEquals("(5, 3) +", visitor.getResult());
    }

    /**
     * Test that a complex expression is correctly converted to string
     * Tests nested operations: (5 + 3) * 2
     */
    @Test
    void testComplexExpression() throws IllegalConstruction {
        // Create expression: (5 + 3) * 2
        List<Expression> params1 = Arrays.asList(new MyNumber(5), new MyNumber(3));
        Operation plus = new Plus(params1);
        List<Expression> params2 = Arrays.asList(plus, new MyNumber(2));
        Operation times = new Times(params2);

        StringVisitor visitor = new StringVisitor(Notation.INFIX);
        times.accept(visitor);
        assertEquals("((5 + 3) * 2)", visitor.getResult());
    }
} 