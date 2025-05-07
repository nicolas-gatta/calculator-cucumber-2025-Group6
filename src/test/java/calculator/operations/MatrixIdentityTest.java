package calculator.operations;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.matrix.Matrix;
import calculator.matrix.MatrixExpression;
import calculator.numbers.ComplexNumber;
import calculator.numbers.RationalNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatrixIdentityTest {

    private MatrixIdentity matrixIdentity;
    private List<Expression> expressions;
    private Matrix matrix;

    @BeforeEach
    void setUp() throws IllegalConstruction {
        expressions = new ArrayList<>();
        matrix = new Matrix(new double[][]{
                {1, 2},
                {3, 4}
        });
        MatrixExpression matrixExpression = new MatrixExpression(matrix);
        expressions.add(matrixExpression);
        matrixIdentity = new MatrixIdentity(expressions);
    }

    @Test
    void testConstructor() {
        assertDoesNotThrow(() -> new MatrixIdentity(expressions));
    }

    @Test
    void testConstructorWithNullList() {
        assertThrows(IllegalConstruction.class, () -> new MatrixIdentity(null));
    }

    @Test
    void testSymbol() {
        assertEquals("I", matrixIdentity.getSymbol());
    }

    @Test
    void testOpMatrix() {
        Matrix result = matrixIdentity.opMatrix(matrix);
        Matrix expected = matrix.identity();
        assertEquals(expected.toString(), result.toString());
    }

    @Test
    void testOpMatrixWithTwoMatrices() {
        Matrix matrix2 = new Matrix(new double[][]{
                {5, 6},
                {7, 8}
        });
        assertThrows(ArithmeticException.class, () -> matrixIdentity.opMatrix(matrix, matrix2));
    }

    @Test
    void testOpInt() {
        assertThrows(ArithmeticException.class, () -> matrixIdentity.op(1, 2));
    }

    @Test
    void testOpReal() {
        assertThrows(ArithmeticException.class, () -> matrixIdentity.opReal(1.0, 2.0));
    }

    @Test
    void testOpRational() {
        RationalNumber r1 = new RationalNumber(1, 2);
        RationalNumber r2 = new RationalNumber(2, 3);
        assertThrows(ArithmeticException.class, () -> matrixIdentity.opRational(r1, r2));
    }

    @Test
    void testOpComplex() {
        ComplexNumber c1 = new ComplexNumber(1, 1);
        ComplexNumber c2 = new ComplexNumber(2, 2);
        assertThrows(ArithmeticException.class, () -> matrixIdentity.opComplex(c1, c2));
    }
}