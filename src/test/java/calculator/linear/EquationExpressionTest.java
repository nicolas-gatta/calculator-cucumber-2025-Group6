package calculator.linear;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.numbers.MyNumber;
import calculator.numbers.RealNumber;
import calculator.operations.Plus;
import expressionParser.StringParser;
import org.antlr.v4.runtime.RecognitionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visitor.EquationCollectorVisitor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EquationExpressionTest {

    private EquationExpression equationExpression;

    @BeforeEach
    void setUp() throws IllegalConstruction {
        Expression left = new Plus(List.of(new MyNumber(1), new MyNumber(2)));
        Expression right = new RealNumber(5.5);
        equationExpression = new EquationExpression(new Equation(left, right));
    }

    @Test
    void testConstructor() {
        assertNotNull(equationExpression);
    }

    @Test
    void testInvalidConstructor() {
        assertThrows(NullPointerException.class, () -> new EquationExpression(null));
    }

    @Test
    void testGetEquation() throws IllegalConstruction {
        Expression left = new Plus(List.of(new MyNumber(1), new MyNumber(2)));
        Expression right = new RealNumber(5.5);
        Equation equation = new Equation(left, right);
        assertEquals(equation.toString(), equationExpression.getEquation().toString());
    }

    @Test
    void testAccept() {
        EquationCollectorVisitor visitor = new EquationCollectorVisitor();
        assertDoesNotThrow(() -> equationExpression.accept(visitor));
    }

    @Test
    void testToString() {
        assertEquals("( 1 + 2 ) = 5.5000", equationExpression.toString());
    }

    @Test
    void testEquals_SameObject() {
        assertEquals(equationExpression, equationExpression);
    }

    @Test
    void testEquals_IdenticalContent() throws IllegalConstruction {
        Expression left = new Plus(List.of(new MyNumber(1), new MyNumber(2)));
        Expression right = new RealNumber(5.5);
        Equation anotherEquation = new Equation(left, right);
        EquationExpression anotherExpression = new EquationExpression(anotherEquation);

        assertEquals(equationExpression, anotherExpression);
    }

    @Test
    void testEquals_DifferentContent() throws IllegalConstruction {
        Expression left = new Plus(List.of(new MyNumber(2), new MyNumber(3)));
        Expression right = new RealNumber(5.5);
        Equation anotherEquation = new Equation(left, right);
        EquationExpression anotherExpression = new EquationExpression(anotherEquation);

        assertNotEquals(equationExpression, anotherExpression);
    }

    @Test
    void testEquals_DifferentType() {
        assertNotEquals(equationExpression, "Not an EquationExpression");
    }

    @Test
    void testHashCode_DifferentObjects() throws IllegalConstruction {
        Expression left = new Plus(List.of(new MyNumber(2), new MyNumber(3)));
        Expression right = new RealNumber(5.5);
        Equation anotherEquation = new Equation(left, right);
        EquationExpression anotherExpression = new EquationExpression(anotherEquation);

        assertNotEquals(equationExpression.hashCode(), anotherExpression.hashCode());
    }
}