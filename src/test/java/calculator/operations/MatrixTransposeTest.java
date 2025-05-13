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

class MatrixTransposeTest {

    private MatrixTranspose matrixTranspose;
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
        matrixTranspose = new MatrixTranspose(expressions);
    }

    @Test
    void testConstructor() {
        assertDoesNotThrow(() -> new MatrixTranspose(expressions));
    }

    @Test
    void testConstructorWithNullList() {
        assertThrows(IllegalConstruction.class, () -> new MatrixTranspose(null));
    }

    @Test
    void testSymbol() {
        assertEquals("T", matrixTranspose.getSymbol());
    }

    @Test
    void testOpMatrix() {
        Matrix result = matrixTranspose.opMatrix(matrix);
        Matrix expected = matrix.transpose();
        assertEquals(expected.toString(), result.toString());
    }

    @Test
    void testOpMatrixWithTwoMatrices() {
        Matrix matrix2 = new Matrix(new double[][]{
                {5, 6},
                {7, 8}
        });
        assertThrows(ArithmeticException.class, () -> matrixTranspose.opMatrix(matrix, matrix2));
    }

    @Test
    void testOpInt() {
        assertThrows(ArithmeticException.class, () -> matrixTranspose.op(1, 2));
    }

    @Test
    void testOpReal() {
        assertThrows(ArithmeticException.class, () -> matrixTranspose.opReal(1.0, 2.0));
    }

    @Test
    void testOpRational() {
        RationalNumber r1 = new RationalNumber(1, 2);
        RationalNumber r2 = new RationalNumber(2, 3);
        assertThrows(ArithmeticException.class, () -> matrixTranspose.opRational(r1, r2));
    }

    @Test
    void testOpMatrixDouble() {
        assertThrows(ArithmeticException.class, () -> matrixTranspose.opMatrix(matrix, 20));
    }

    @Test
    void testOpComplex() {
        ComplexNumber c1 = new ComplexNumber(1, 1);
        ComplexNumber c2 = new ComplexNumber(2, 2);
        assertThrows(ArithmeticException.class, () -> matrixTranspose.opComplex(c1, c2));
    }
}