package calculator.linear;

import calculator.numbers.MyNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquationTest {

    @Test
    public void testConstructor(){
        var left = new MyNumber(5);
        var right = new MyNumber(10);

        assertDoesNotThrow(() -> new Equation(left, right));
    }

    @Test
    public void testGetters(){
        var left = new MyNumber(5);
        var right = new MyNumber(10);
        Equation eq = new Equation(left, right);

        assertEquals(left, eq.getLeft());
        assertEquals(right, eq.getRight());
    }
    @Test
    public void TestToString() {
        var left = new MyNumber(5);
        var right = new MyNumber(10);
        Equation eq = new Equation(left, right);

        assertEquals("5 = 10", eq.toString());
    }

}