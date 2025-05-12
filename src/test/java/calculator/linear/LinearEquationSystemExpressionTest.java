package calculator.linear;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.numbers.MyNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.operations.Plus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visitor.EquationCollectorVisitor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinearEquationSystemExpressionTest {

    private LinearEquationSystemExpression system;
    private EquationExpression expr1;
    private EquationExpression expr2;

    @BeforeEach
    void setUp() throws IllegalConstruction {
        Expression left1 = new Plus(List.of(new VariableExpression("y"), new VariableExpression(new RealNumber(8),"z")));
        Expression right1 = new RealNumber(5.5);
        expr1 = new EquationExpression(new Equation(left1, right1));

        Expression left2 = new Plus(List.of(new VariableExpression("y"), new VariableExpression(new RationalNumber(8,3),"z")));
        Expression right2 = new RealNumber(7.0);
        expr2 = new EquationExpression(new Equation(left2, right2));

        system = new LinearEquationSystemExpression(List.of(expr1, expr2));
    }

    @Test
    void testConstructor() {
        assertNotNull(system);
        assertEquals(2, system.getSystem().size());
    }

    @Test
    void testToString() {
        String expected = "((y + 8.0000z) = 5.5000, (y + 2.6667z) = 7.0000)";
        assertEquals(expected, system.toString());
    }

    @Test
    void testSolve() {
        String expected = "(y = 7.7500, z = -0.2813)";
        assertEquals(expected, system.solve().toString());
    }

    @Test
    void testGetSystem() {
        assertEquals(2, system.getSystem().size());
        assertTrue(system.getSystem().contains(expr1));
        assertTrue(system.getSystem().contains(expr2));
    }

    @Test
    void testAcceptCallsVisitor() {
        EquationCollectorVisitor visitor = new EquationCollectorVisitor();
        assertDoesNotThrow(() -> system.accept(visitor));
        assertEquals(2, visitor.getAllVariables().size());
    }

    @Test
    void testEquals_SameObject() {
        assertEquals(system, system);
    }

    @Test
    void testEquals_DifferentContent() throws IllegalConstruction {
        Expression left = new Plus(List.of(new MyNumber(2), new MyNumber(3)));
        Expression right = new RealNumber(8.0);
        EquationExpression anotherExpr = new EquationExpression(new Equation(left, right));

        LinearEquationSystemExpression differentSystem = new LinearEquationSystemExpression(List.of(anotherExpr));
        assertNotEquals(system, differentSystem);
    }
}