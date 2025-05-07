package calculator.linear;

import calculator.numbers.MyNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VariableExpressionTest {

    @Test
    public void testConstructorInteger(){
        MyNumber coeff = new MyNumber(3);

        assertDoesNotThrow(() -> new VariableExpression(coeff, "x"));
    }

    @Test
    public void testConstructorRealNumber(){
        RealNumber coeff = new RealNumber(3);

        assertDoesNotThrow(() -> new VariableExpression(coeff, "x"));
    }

    @Test
    public void testConstructorRationalNumber(){
        RationalNumber coeff = new RationalNumber(3, 8);

        assertDoesNotThrow(() -> new VariableExpression(coeff, "x"));
    }

    @Test
    public void testGetters() {
        RealNumber coeff = new RealNumber(3);
        VariableExpression var = new VariableExpression(coeff, "x");

        assertEquals(coeff, var.getLeft());
        assertEquals("x", var.getRight());
        assertEquals("3.0000x", var.toString());
    }

    @Test
    public void testToStringDefaultCoefficient() {
        VariableExpression var = new VariableExpression("y");
        assertEquals("y", var.toString());
    }

}