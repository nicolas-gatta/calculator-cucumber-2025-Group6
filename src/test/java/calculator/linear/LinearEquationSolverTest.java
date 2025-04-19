package calculator.linear;

import calculator.matrix.Matrix;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinearEquationSolverTest {
    @Test
    public void testSolveValidSystem2x2() {
        Matrix A = new Matrix(new double[][]{{2, 3}, {1, -1}});

        Matrix b = new Matrix(new double[][]{{8}, {0}});

        List<String> variables = List.of("x", "y");
        LinearEquationSystemExpression solution = LinearEquationSolver.solve(A, b, variables);

        String expected = "(x = 1,6000, y = 1,6000)";
        assertEquals(expected, solution.toString());
    }

    @Test
    public void testSolveValidSystem3x3() {
        Matrix A = new Matrix(new double[][]{{3, 3, 0}, {3, 0, -4}, {1,-1,-1}});

        Matrix b = new Matrix(new double[][]{{5}, {7}, {10}});

        List<String> variables = List.of("x", "y", "z");
        LinearEquationSystemExpression solution = LinearEquationSolver.solve(A, b, variables);

        String expected = "(x = 7,9333, y = -6,2667, z = 4,2000)";
        assertEquals(expected, solution.toString());
    }

    @Test
    public void testSolveThrowsWhenDeterminantZero() {
        Matrix A = new Matrix(new double[][]{
                {1, 2},
                {2, 4}
        });

        Matrix b = new Matrix(new double[][]{
                {5},
                {10}
        });

        List<String> variables = List.of("x", "y");

        assertThrows(IllegalArgumentException.class, () ->
                LinearEquationSolver.solve(A, b, variables));
    }
}