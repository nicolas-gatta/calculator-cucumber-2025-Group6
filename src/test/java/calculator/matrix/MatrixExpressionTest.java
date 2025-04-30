package calculator.matrix;

import calculator.matrix.Matrix;
import calculator.matrix.MatrixExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MatrixExpressionTest {

    @Test
    void testConstructorAndGetter() {
        Matrix matrix = new Matrix("[[1,2],[3,4]]");
        MatrixExpression expr = new MatrixExpression(matrix);
        assertEquals(matrix.toString(), expr.getMatrix().toString());
    }

    @Test
    void testToString() {
        Matrix matrix = new Matrix("[[1,2],[3,4]]");
        MatrixExpression expr = new MatrixExpression(matrix);
        assertEquals("[[1.0,2.0],[3.0,4.0]]", expr.toString());
    }

    @Test
    void testEqualsSameObject() {
        Matrix matrix = new Matrix("[[1,2],[3,4]]");
        MatrixExpression expr = new MatrixExpression(matrix);
        assertEquals(expr, expr); // same instance
    }

    @Test
    void testEqualsDifferentObjectSameMatrix() {
        Matrix matrix1 = new Matrix("[[1,2],[3,4]]");
        Matrix matrix2 = new Matrix("[[1,2],[3,4]]");
        MatrixExpression expr1 = new MatrixExpression(matrix1);
        MatrixExpression expr2 = new MatrixExpression(matrix2);
        assertEquals(expr1, expr2);
        assertEquals(expr1.hashCode(), expr2.hashCode());
    }

    @Test
    void testNotEqualsDifferentMatrix() {
        Matrix matrix1 = new Matrix("[[1,2],[3,4]]");
        Matrix matrix2 = new Matrix("[[5,6],[7,8]]");
        MatrixExpression expr1 = new MatrixExpression(matrix1);
        MatrixExpression expr2 = new MatrixExpression(matrix2);
        assertNotEquals(expr1, expr2);
    }

    @Test
    void testNotEqualsOtherType() {
        Matrix matrix = new Matrix("[[1,2],[3,4]]");
        MatrixExpression expr = new MatrixExpression(matrix);
        assertNotEquals(expr, "NotAMatrixExpression");
    }
}
