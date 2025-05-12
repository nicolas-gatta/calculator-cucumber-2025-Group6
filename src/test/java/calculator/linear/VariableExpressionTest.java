package calculator.linear;

import calculator.numbers.ComplexNumber;
import calculator.numbers.MyNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VariableExpressionTest {

    @Test
    void testConstructorInteger(){
        MyNumber coeff = new MyNumber(3);

        assertDoesNotThrow(() -> new VariableExpression(coeff, "x"));
    }

    @Test
    void testConstructorRealNumber(){
        RealNumber coeff = new RealNumber(3);

        assertDoesNotThrow(() -> new VariableExpression(coeff, "x"));
    }

    @Test
    void testConstructorRationalNumber(){
        RationalNumber coeff = new RationalNumber(3, 8);

        assertDoesNotThrow(() -> new VariableExpression(coeff, "x"));
    }

    @Test
    void testConstructorIllegalType(){
        ComplexNumber coeff = new ComplexNumber(3, 8);

        assertThrows(IllegalArgumentException.class, () -> new VariableExpression(coeff, "x"));
    }

    @Test
    void testGetters() {
        RealNumber coeff = new RealNumber(3);
        VariableExpression variable = new VariableExpression(coeff, "x");

        assertEquals(coeff, variable.getLeft());
        assertEquals("x", variable.getRight());
        assertEquals("3.0000x", variable.toString());
    }

    @Test
    void testToStringDefaultCoefficient() {
        VariableExpression variable = new VariableExpression("y");
        assertEquals("y", variable.toString());
    }

    @Test
    void testEqualsSameReference() {
        VariableExpression variable = new VariableExpression("x");
        assertEquals(variable, variable);
    }

    @Test
    void testEqualsSameValues() {
        VariableExpression var1 = new VariableExpression(new RealNumber(3), "x");
        VariableExpression var2 = new VariableExpression(new RealNumber(3), "x");

        assertEquals(var1, var2);
        assertEquals(var2, var1);
        assertEquals(var1.hashCode(), var2.hashCode());
    }

    @Test
    void testNotEqualsDifferentCoefficient() {
        VariableExpression var1 = new VariableExpression(new RealNumber(2), "x");
        VariableExpression var2 = new VariableExpression(new RealNumber(3), "x");

        assertNotEquals(var1, var2);
    }

    @Test
    void testNotEqualsDifferentVariable() {
        VariableExpression var1 = new VariableExpression(new RealNumber(3), "x");
        VariableExpression var2 = new VariableExpression(new RealNumber(3), "y");

        assertNotEquals(var1, var2);
    }

    @Test
    void testNotEqualsNull() {
        VariableExpression variable = new VariableExpression("x");
        assertNotEquals(variable, null);
    }

    @Test
    void testNotEqualsDifferentClass() {
        VariableExpression variable = new VariableExpression("x");
        assertNotEquals(variable, "x");
    }

}