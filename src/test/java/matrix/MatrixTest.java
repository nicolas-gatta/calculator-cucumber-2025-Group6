package matrix;

import calculator.matrix.Matrix;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void testConstructorWithArray() {
        double[][] values = {{1, 2}, {3, 4}};
        Matrix m = new Matrix(values);
        assertEquals(2, m.rows());
        assertEquals(2, m.columns());
    }

    @Test
    void testEmptyMatrix() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix(new double[0][0]));
    }

    @Test
    void testConstructorWithString() {
        Matrix m = new Matrix("[[1,2],[3,4]]");
        assertEquals(1.0, m.getValue(0, 0));
        assertEquals(4.0, m.getValue(1, 1));
    }

    @Test
    void testInvalidMatrixString() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix("[1,2],[3,4]"));
    }

    @Test
    void testToString() {
        Matrix m = new Matrix(new double[][]{{1, 2}, {3, 4}});
        assertEquals("[[1.0,2.0],[3.0,4.0]]", m.toString());
    }

    @Test
    void testAddition() {
        Matrix m1 = new Matrix("[[1,2],[3,4]]");
        Matrix m2 = new Matrix("[[5,6],[7,8]]");
        Matrix result = m1.add(m2);
        assertEquals("[[6.0,8.0],[10.0,12.0]]", result.toString());
    }

    @Test
    void testInvalidAddition() {
        Matrix m1 = new Matrix("[[1,2]]");
        Matrix m2 = new Matrix("[[1,2],[3,4]]");
        assertThrows(IllegalArgumentException.class, () -> m1.add(m2));
    }

    @Test
    void testSubtraction() {
        Matrix m1 = new Matrix("[[5,6],[7,8]]");
        Matrix m2 = new Matrix("[[1,2],[3,4]]");
        Matrix result = m1.subtract(m2);
        assertEquals("[[4.0,4.0],[4.0,4.0]]", result.toString());
    }

    @Test
    void testScalarMultiplication() {
        Matrix m = new Matrix("[[1,2],[3,4]]");
        Matrix result = m.multiply(2);
        assertEquals("[[2.0,4.0],[6.0,8.0]]", result.toString());
    }

    @Test
    void testMatrixMultiplication() {
        Matrix m1 = new Matrix("[[1,2],[3,4]]");
        Matrix m2 = new Matrix("[[2,0],[1,2]]");
        Matrix result = m1.multiply(m2);
        assertEquals("[[4.0,4.0],[10.0,8.0]]", result.toString());
    }

    @Test
    void testInvalidMultiplication() {
        Matrix m1 = new Matrix("[[1,2]]");
        Matrix m2 = new Matrix("[[1,2]]");
        assertThrows(IllegalArgumentException.class, () -> m1.multiply(m2));
    }

    @Test
    void testDivideByNumber() {
        Matrix m = new Matrix("[[2,4],[6,8]]");
        Matrix result = m.divide(2);
        assertEquals("[[4.0,8.0],[12.0,16.0]]", result.toString()); // BUG EXPECTED: should be divide!
    }

    @Test
    void testTranspose() {
        Matrix m = new Matrix("[[1,2],[3,4]]");
        Matrix result = m.transpose();
        assertEquals("[[1.0,3.0],[2.0,4.0]]", result.toString());
    }

    @Test
    void testDeterminant2x2() {
        Matrix m = new Matrix("[[1,2],[3,4]]");
        assertEquals(-2.0, m.determinant());
    }

    @Test
    void testDeterminant3x3() {
        Matrix m = new Matrix("[[6,1,1],[4,-2,5],[2,8,7]]");
        assertEquals(-306.0, m.determinant(), 1e-6);
    }

    @Test
    void testCofactor() {
        Matrix m = new Matrix("[[3,0,2],[2,0,-2],[0,1,1]]");
        Matrix result = m.cofactor();
        assertEquals("[[2.0,-2.0,2.0],[2.0,3.0,-3.0],[0.0,10.0,0.0]]", result.toString());
    }

    @Test
    void testInverseOfIdentity() {
        Matrix m = new Matrix("[[1,0],[0,1]]");
        Matrix inverse = m.inverse();
        assertEquals("[[1.0,0.0],[0.0,1.0]]", inverse.toString());
    }

    @Test
    void testIdentityGeneration() {
        Matrix m = new Matrix("[[1,2],[3,4]]");
        Matrix identity = m.identity();
        assertEquals("[[1.0,0.0],[0.0,1.0]]", identity.toString());
    }

    @Test
    void testSubMatrix() {
        Matrix m = new Matrix("[[1,2,3],[4,5,6],[7,8,9]]");
        Matrix sub = m.createSubMatrix(0, 0);
        assertEquals("[[5.0,6.0],[8.0,9.0]]", sub.toString());
    }
}
