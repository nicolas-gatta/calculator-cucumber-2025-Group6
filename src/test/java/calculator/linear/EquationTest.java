package calculator.linear;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.numbers.MyNumber;
import calculator.numbers.RealNumber;
import calculator.operations.Plus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EquationTest {

    private Equation equation;


    @BeforeEach
    void setUp() throws IllegalConstruction {
        Expression left = new Plus(List.of(new MyNumber(1), new MyNumber(2)));
        Expression right = new RealNumber(5.5);
        equation = new Equation(left, right);
    }

    @Test
    void testConstructor(){
        var left = new MyNumber(5);
        var right = new MyNumber(10);

        assertDoesNotThrow(() -> new Equation(left, right));
    }

    @Test
    void testInvalidConstructor(){
        MyNumber myNumber = new MyNumber(5);
        assertThrows(NullPointerException.class, () -> new Equation(null,myNumber));
    }

    @Test
    void testInvalidConstructor2(){
        MyNumber number = new MyNumber(5);
        assertThrows(NullPointerException.class, () -> new Equation(number,null)); }

    @Test
    void testGetters(){
        var left = new MyNumber(5);
        var right = new MyNumber(10);
        Equation eq = new Equation(left, right);

        assertEquals(left, eq.getLeft());
        assertEquals(right, eq.getRight());
    }

    @Test
    void testGetEquation() throws IllegalConstruction {
        Expression left = new Plus(List.of(new MyNumber(1), new MyNumber(2)));
        Expression right = new RealNumber(5.5);
        Equation equation = new Equation(left, right);
        assertEquals(equation.toString(), equation.toString());
    }

    @Test
    void testToString() {
        assertEquals("(1 + 2) = 5.5000", equation.toString());
    }

    @Test
    void testEquals_SameObject() {
        assertEquals(equation, equation);
    }

    @Test
    void testEquals_IdenticalContent() throws IllegalConstruction {
        Expression left = new Plus(List.of(new MyNumber(1), new MyNumber(2)));
        Expression right = new RealNumber(5.5);
        Equation anotherEquation = new Equation(left, right);

        assertEquals(equation.toString(), anotherEquation.toString());
    }

    @Test
    void testEquals_DifferentContent() throws IllegalConstruction {
        Expression left = new Plus(List.of(new MyNumber(2), new MyNumber(3)));
        Expression right = new RealNumber(5.5);
        Equation anotherEquation = new Equation(left, right);

        assertNotEquals(equation, anotherEquation);
    }

    @Test
    void testEquals_DifferentType() {
        assertNotEquals(equation, "Not an Equation");
    }

    @Test
    void testHashCode_DifferentObjects() throws IllegalConstruction {
        Expression left = new Plus(List.of(new MyNumber(2), new MyNumber(3)));
        Expression right = new RealNumber(5.5);
        Equation anotherEquation = new Equation(left, right);

        assertNotEquals(equation.hashCode(), anotherEquation.hashCode());
    }

}