package calculator.linear;

import calculator.numbers.MyNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquationExpressionTest {

    @Test
    public void testConstructor(){
        Equation eq = new Equation(new MyNumber(1), new MyNumber(2));

        assertDoesNotThrow(() -> new EquationExpression(eq));
    }

    @Test
    public void testoString() {
        Equation eq = new Equation(new MyNumber(1), new MyNumber(2));
        EquationExpression expr = new EquationExpression(eq);

        assertEquals(eq, expr.getEquation());
        assertEquals("1 = 2", expr.toString());
    }
}