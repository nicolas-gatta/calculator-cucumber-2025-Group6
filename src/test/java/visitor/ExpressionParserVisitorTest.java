package visitor;

import calculator.Expression;
import calculator.linear.LinearEquationSystemExpression;
import calculator.linear.VariableExpression;
import calculator.matrix.MatrixExpression;
import calculator.numbers.ComplexNumber;
import calculator.numbers.MyNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.operations.*;
import expressionParser.StringParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserVisitorTest {

    @Test
     void testIntegerParsing() {
        Expression expr = StringParser.parse("42");
        assertNotNull(expr);
        assertInstanceOf(MyNumber.class, expr);
        assertEquals("42", expr.toString());
    }

    @Test
     void testVariableParsing() {
        Expression expr = StringParser.parse("42a");
        assertNotNull(expr);
        assertInstanceOf(VariableExpression.class, expr);
        assertEquals("42.0000a", expr.toString());
    }

    @Test
     void testRationalParsing() {
        Expression expr = StringParser.parse("3/4");
        assertNotNull(expr);
        assertInstanceOf(RationalNumber.class, expr);
        assertEquals("3/4", expr.toString());
    }

    @Test
     void testRealParsing() {
        Expression expr = StringParser.parse("3.14");
        assertNotNull(expr);
        assertInstanceOf(RealNumber.class, expr);
        assertEquals("3.1400", expr.toString());
    }

    @Test
     void testComplexParsing() {
        Expression expr = StringParser.parse("(1.0 + 2.0i)");
        assertNotNull(expr);
        assertInstanceOf(ComplexNumber.class, expr);
        ComplexNumber cn = (ComplexNumber) expr;
        assertEquals(1.0, cn.getReal());
        assertEquals(2.0, cn.getImaginary());
        assertEquals("1.0 + 2.0i", cn.toString());
    }

    @Test
     void testAddition() {
        Expression expr = StringParser.parse("3 + 4");
        assertNotNull(expr);
        assertInstanceOf(Plus.class, expr);
        assertEquals("(3 + 4)", expr.toString());
    }

    @Test
     void testSubtraction() {
        Expression expr = StringParser.parse("3 - 4");
        assertNotNull(expr);
        assertInstanceOf(Minus.class, expr);
        assertEquals("(3 - 4)", expr.toString());
    }

    @Test
     void testMultiplication() {
        Expression expr = StringParser.parse("3 * 4");
        assertNotNull(expr);
        assertInstanceOf(Times.class, expr);
        assertEquals("(3 * 4)", expr.toString());
    }

    @Test
    void testDivided() {
        Expression expr = StringParser.parse("3 / 4");
        assertNotNull(expr);
        assertInstanceOf(Divides.class, expr);
        assertEquals("(3 / 4)", expr.toString());
    }

    @Test
     void testPrefix() {
        Expression expr = StringParser.parse("(/(+(1,2,3),5))");
        assertNotNull(expr);
        assertEquals("/ (+ (1, 2, 3), 5)", expr.toString());
    }

    @Test
     void testInfixtfix() {
        Expression expr = StringParser.parse("(50 + (3 / 4)) * 2");
        assertNotNull(expr);
        assertEquals("((50 + (3 / 4)) * 2)", expr.toString());
    }

    @Test
     void testPostfix() {
        Expression expr = StringParser.parse("((1,2,5,3)+,5.5)/");
        assertNotNull(expr);
        assertEquals("((1, 2, 5, 3) +, 5.5000) /", expr.toString());
    }

    @Test
     void testMatrixParsing() {
        Expression expr = StringParser.parse("[[1,2],[3,4]]");
        assertNotNull(expr);
        assertInstanceOf(MatrixExpression.class, expr);
        assertEquals("[[1.0,2.0],[3.0,4.0]]", expr.toString());
    }

    @Test
     void testMatrixAddition() {
        Expression expr = StringParser.parse("[[1,2],[3,4]] + [[1,2],[3,4]]");
        assertNotNull(expr);
        assertInstanceOf(Plus.class, expr);
        assertEquals("([[1.0,2.0],[3.0,4.0]] + [[1.0,2.0],[3.0,4.0]])", expr.toString());
    }

    @Test
     void testMatrixSubtraction() {
        Expression expr = StringParser.parse("[[1,2],[3,4]] - [[1,2],[3,4]]");
        assertNotNull(expr);
        assertInstanceOf(Minus.class, expr);
        assertEquals("([[1.0,2.0],[3.0,4.0]] - [[1.0,2.0],[3.0,4.0]])", expr.toString());
    }

    @Test
     void testMatrixMultiplication() {
        Expression expr = StringParser.parse("[[1,2],[3,4]] * [[1,2],[3,4]]");
        assertNotNull(expr);
        assertInstanceOf(Times.class, expr);
        assertEquals("([[1.0,2.0],[3.0,4.0]] * [[1.0,2.0],[3.0,4.0]])", expr.toString());
    }

    @Test
     void testMatrixMultiplicationScalar() {
        Expression expr = StringParser.parse("[[1,2],[3,4]] * 5");
        assertNotNull(expr);
        assertInstanceOf(Times.class, expr);
        assertEquals("([[1.0,2.0],[3.0,4.0]] * 5)", expr.toString());
    }

    @Test
     void testMatrixDivision() {
        Expression expr = StringParser.parse("[[1,2],[3,4]] / 5");
        assertNotNull(expr);
        assertInstanceOf(Divides.class, expr);
        assertEquals("([[1.0,2.0],[3.0,4.0]] / 5)", expr.toString());
    }

    @Test
     void testMatrixTransposePrefix() {
        Expression expr = StringParser.parse("T^ [[1,0],[0,1]]");
        assertNotNull(expr);
        assertInstanceOf(MatrixTranspose.class, expr);
        assertEquals("T^([[1.0,0.0],[0.0,1.0]])", expr.toString());
    }

    @Test
     void testMatrixTransposePostfix() {
        Expression expr = StringParser.parse("[[1,0],[0,1]] ^T");
        assertNotNull(expr);
        assertInstanceOf(MatrixTranspose.class, expr);
        assertEquals("([[1.0,0.0],[0.0,1.0]])^T", expr.toString());
    }

    @Test
     void testMatrixInvertedPrefix() {
        Expression expr = StringParser.parse("-1^ [[2,0],[0,3]]");
        assertNotNull(expr);
        assertInstanceOf(MatrixInverted.class, expr);
        assertEquals("-1^([[2.0,0.0],[0.0,3.0]])", expr.toString());
    }

    @Test
     void testMatrixInvertedPostfix() {
        Expression expr = StringParser.parse("[[1,0],[0,1]] ^-1");
        assertNotNull(expr);
        assertInstanceOf(MatrixInverted.class, expr);
        assertEquals("([[1.0,0.0],[0.0,1.0]])^-1", expr.toString());
    }

    @Test
     void testMatrixIdentityPrefix() {
        Expression expr = StringParser.parse("I^ [[4,0],[0,5]]");
        assertNotNull(expr);
        assertInstanceOf(MatrixIdentity.class, expr);
        assertEquals("I^([[4.0,0.0],[0.0,5.0]])", expr.toString());
    }

    @Test
     void testMatrixIdentityPostfix() {
        Expression expr = StringParser.parse("[[5,0],[0,8]] ^I");
        assertNotNull(expr);
        assertInstanceOf(MatrixIdentity.class, expr);
        assertEquals("([[5.0,0.0],[0.0,8.0]])^I", expr.toString());
    }

    @Test
     void testLinearEquationSystemParsing() {
        Expression expr = StringParser.parse("solve((x + y) = 5, (x - y) = 1)");
        assertInstanceOf(LinearEquationSystemExpression.class, expr);
        assertEquals(2, ((LinearEquationSystemExpression) expr).getSystem().size());
    }
}