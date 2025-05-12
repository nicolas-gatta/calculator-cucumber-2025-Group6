package calculator.linear;

import calculator.matrix.Matrix;
import calculator.numbers.RealNumber;

import java.util.*;

/**
 * Provides a static method to solve a system of linear equations represented by a matrix.
 */
public class LinearEquationSolver {

    /**
     * Private constructor to prevent instantiation as this is a utility class with only static methods.
     */
    private LinearEquationSolver() {
        // Private constructor to prevent instantiation
    }

    /**
     * Solves a linear system Ax = b and returns it as a LinearEquationSystemExpression.
     *
     * @param A         the coefficient matrix
     * @param b         the constants vector matrix
     * @param variables the list of variable names corresponding to the columns of A
     * @return a LinearEquationSystemExpression representing the solution
     * @throws IllegalArgumentException if the matrix A has no unique solution
     */
    public static LinearEquationSystemExpression solve(Matrix A, Matrix b, List<String> variables) {

        if (A.determinant() == 0) {
            throw new IllegalArgumentException("No unique solution: determinant is 0.");
        }

        List<EquationExpression> equations = new ArrayList<>();

        Matrix invertedMatrix = A.inverse();

        Matrix result = invertedMatrix.multiply(b);

        for (int i = 0; i < variables.size(); i++) {

            Equation equation = new Equation(new VariableExpression(variables.get(i)), new RealNumber(result.getValue(i, 0)));
            EquationExpression equationExpression = new EquationExpression(equation);
            equations.add(equationExpression);
        }

        return new LinearEquationSystemExpression(equations);
    }
}
