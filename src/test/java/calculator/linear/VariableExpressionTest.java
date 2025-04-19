package calculator.linear;

import calculator.numbers.MyNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VariableExpressionTest {

    @Test
    public void testConstructor(){
        MyNumber coeff = new MyNumber(3);

        assertDoesNotThrow(() -> new VariableExpression(coeff, "x"));
    }

    @Test
    public void testGetters() {
        MyNumber coeff = new MyNumber(3);
        VariableExpression var = new VariableExpression(coeff, "x");

        assertEquals(coeff, var.getLeft());
        assertEquals("x", var.getRight());
        assertEquals("3x", var.toString());
    }

    @Test
    public void testToStringDefaultCoefficient() {
        VariableExpression var = new VariableExpression("y");
        assertEquals("y", var.toString());
    }

}