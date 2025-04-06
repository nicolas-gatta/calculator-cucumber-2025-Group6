package matrix;

import matrix.Matrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    private Matrix matrix;

    @BeforeEach
    void setUp() {
        double[][] m = {{1,2}, {3,4}, {5,6}};
        matrix = new Matrix(m);
    }

    @Test
    void rows() {
        assertEquals(3, matrix.rows());
    }

    @Test
    void columns() {
        assertEquals(2, matrix.columns());
    }

    @Test
    void testToString() {
        assertEquals("[[1.0,2.0],[3.0,4.0],[5.0,6.0]]", matrix.toString());
    }
}