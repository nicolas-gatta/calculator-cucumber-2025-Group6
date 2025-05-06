package calculator.matrix;

import calculator.matrix.Matrix;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void testConstructorWithArray2x2() {
        double[][] values = {{1, 2}, {3, 4}};
        Matrix m = new Matrix(values);
        assertEquals(2, m.rows());
        assertEquals(2, m.columns());
    }

    @Test
    void testConstructorWithArray3x3() {
        double[][] values = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix m = new Matrix(values);
        assertEquals(3, m.rows());
        assertEquals(3, m.columns());
    }

    @Test
    void testConstructorWithArray3x1() {
        double[] values = {1,2,3};
        Matrix m = new Matrix(values);
        assertEquals(3, m.rows());
        assertEquals(1, m.columns());
    }

    @Test
    void testEmptyMatrix() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix(new double[0][0]));
    }

    @Test
    void testConstructorWithString2x2() {
        Matrix m = new Matrix("[[1,2],[3,4]]");
        assertEquals(1.0, m.getValue(0, 0));
        assertEquals(4.0, m.getValue(1, 1));
    }

    @Test
    void testConstructorWithString3x3() {
        Matrix m = new Matrix("[[1,2,3],[4,5,6],[7,8,9]]");
        assertEquals(1.0, m.getValue(0, 0));
        assertEquals(5.0, m.getValue(1, 1));
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
    void testAddition2x2() {
        Matrix m1 = new Matrix("[[1,2],[3,4]]");
        Matrix m2 = new Matrix("[[5,6],[7,8]]");
        Matrix result = m1.add(m2);
        assertEquals("[[6.0,8.0],[10.0,12.0]]", result.toString());
    }

    @Test
    void testAddition3x3() {
        Matrix m1 = new Matrix("[[1,2,3],[4,5,6],[7,8,9]]");
        Matrix m2 = new Matrix("[[1,2,3],[4,5,6],[7,8,9]]");
        Matrix result = m1.add(m2);
        assertEquals("[[2.0,4.0,6.0],[8.0,10.0,12.0],[14.0,16.0,18.0]]", result.toString());
    }

    @Test
    void testInvalidAddition() {
        Matrix m1 = new Matrix("[[1,2]]");
        Matrix m2 = new Matrix("[[1,2],[3,4]]");
        assertThrows(IllegalArgumentException.class, () -> m1.add(m2));
    }

    @Test
    void testSubtraction2x2() {
        Matrix m1 = new Matrix("[[5,6],[7,8]]");
        Matrix m2 = new Matrix("[[1,2],[3,4]]");
        Matrix result = m1.subtract(m2);
        assertEquals("[[4.0,4.0],[4.0,4.0]]", result.toString());
    }

    @Test
    void testSubtraction3x3() {
        Matrix m1 = new Matrix("[[1,2,3],[4,5,6],[7,8,9]]");
        Matrix m2 = new Matrix("[[1,2,3],[4,5,6],[7,8,9]]");
        Matrix result = m1.subtract(m2);
        assertEquals("[[0.0,0.0,0.0],[0.0,0.0,0.0],[0.0,0.0,0.0]]", result.toString());
    }

    @Test
    void testInvalidSubtraction() {
        Matrix m1 = new Matrix("[[5,6],[7,8]]");
        Matrix m2 = new Matrix("[[1,2,3],[4,5,6],[7,8,9]]");
        assertThrows(IllegalArgumentException.class, () -> m1.subtract(m2));
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
    void testMatrixMultiplicationDifferentSize() {
        Matrix m1 = new Matrix("[[1,2],[3,4],[5,6]]");
        Matrix m2 = new Matrix("[[2],[4]]");
        Matrix result = m1.multiply(m2);
        assertEquals("[[10.0],[22.0],[34.0]]", result.toString());
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
        assertEquals("[[1.0,2.0],[3.0,4.0]]", result.toString());
    }

    @Test
    void testTranspose2x2() {
        Matrix m = new Matrix("[[1,2],[3,4]]");
        Matrix result = m.transpose();
        assertEquals("[[1.0,3.0],[2.0,4.0]]", result.toString());
    }

    @Test
    void testTranspose3x3() {
        Matrix m = new Matrix("[[1,2,3],[4,5,6],[7,8,9]]");
        Matrix result = m.transpose();
        assertEquals("[[1.0,4.0,7.0],[2.0,5.0,8.0],[3.0,6.0,9.0]]", result.toString());
    }


    @Test
    void testDeterminant2x2() {
        Matrix m = new Matrix("[[1,2],[3,4]]");
        assertEquals(-2.0, m.determinant());
    }

    @Test
    void testDeterminant3x3() {
        Matrix m = new Matrix("[[-4,-6,2],[5,-1,3],[-2,4,-3]]");
        assertEquals(18, m.determinant());
    }

    @Test
    void testAdjoint2x2() {
        Matrix m = new Matrix("[[3,0],[2,0]]");
        Matrix result = m.adjoint();
        assertEquals("[[0.0,0.0],[-2.0,3.0]]", result.toString());
    }

    @Test
    void testAdjoint3x3() {
        Matrix m = new Matrix("[[1,3,2],[0,2,2],[-2,-1,0]]");
        Matrix result = m.adjoint();
        assertEquals("[[2.0,-2.0,2.0],[-4.0,4.0,-2.0],[4.0,-5.0,2.0]]", result.toString());
    }

    @Test
    void testInverse2x2() {
        Matrix m = new Matrix("[[3,1],[2,1]]");
        Matrix result = m.inverse();
        assertEquals("[[1.0,-1.0],[-2.0,3.0]]", result.toString());
    }

    @Test
    void testInverse3x3() {
        Matrix m = new Matrix("[[1,3,2],[0,2,2],[-2,-1,0]]");
        Matrix result = m.inverse();
        assertEquals("[[-1.0,1.0,-1.0],[2.0,-2.0,1.0],[-2.0,2.5,-1.0]]", result.toString());
    }

    @Test
    void testInvalidInverse() {
        Matrix m = new Matrix("[[3,0],[2,0]]");
        assertThrows(IllegalArgumentException.class, () -> m.inverse());
    }

    @Test
    void testCofactor2x2() {
        Matrix m = new Matrix("[[3,0],[2,0]]");
        Matrix result = m.cofactor();
        assertEquals("[[0.0,-2.0],[0.0,3.0]]", result.toString());
    }

    @Test
    void testCofactor3x3() {
        Matrix m = new Matrix("[[-4,-6,2],[5,-1,3],[-2,4,-3]]");
        Matrix result = m.cofactor();
        assertEquals("[[-9.0,9.0,18.0],[-10.0,16.0,28.0],[-16.0,22.0,34.0]]", result.toString());
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
