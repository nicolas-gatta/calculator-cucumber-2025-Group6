package calculator.linear;

import calculator.numbers.MyNumber;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinearEquationSystemExpressionTest {

    @Test
    public void testConstructor(){
        Equation eq = new Equation(new VariableExpression("x"), new MyNumber(3));
        EquationExpression expr = new EquationExpression(eq);

        assertDoesNotThrow(() -> new LinearEquationSystemExpression(List.of(expr)));
    }

    @Test
    public void testToStringAndGetSystem() {
        Equation eq = new Equation(new VariableExpression("x"), new MyNumber(3));
        EquationExpression expr = new EquationExpression(eq);

        LinearEquationSystemExpression system = new LinearEquationSystemExpression(List.of(expr));

        assertEquals("(x = 3)", system.toString());
        assertEquals(1, system.getSystem().size());
        assertEquals(expr, system.getSystem().get(0));
    }

    @Test
    public void testAcceptCallsVisitor() {
        var system = new LinearEquationSystemExpression(List.of());
        var mockVisitor = new visitor.EquationCollectorVisitor();

        assertDoesNotThrow(() -> system.accept(mockVisitor));
    }
}