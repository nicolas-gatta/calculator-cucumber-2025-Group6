package calculator.linear;

import calculator.matrix.Matrix;

import java.util.*;

public class LinearEquationSolver {

    public static Map<String, Double> solve(Matrix matrix, double[] b, List<String> variables) {

        double[][] A = matrix.getValues();

        if (matrix.determinant() == 0) {
            throw new IllegalArgumentException("No unique solution: determinant is 0.");
        }

        int n = A.length;
        double[][] mat = new double[n][n + 1];

        // Build augmented matrix [A|b]
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, mat[i], 0, n);
            mat[i][n] = b[i];
        }

        // Gaussian elimination
        for (int i = 0; i < n; i++) {
            // Find pivot
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(mat[k][i]) > Math.abs(mat[maxRow][i])) {
                    maxRow = k;
                }
            }

            // Swap rows
            double[] temp = mat[i];
            mat[i] = mat[maxRow];
            mat[maxRow] = temp;

            // Eliminate below
            for (int k = i + 1; k < n; k++) {
                double factor = mat[k][i] / mat[i][i];
                for (int j = i; j <= n; j++) {
                    mat[k][j] -= factor * mat[i][j];
                }
            }
        }

        // Back substitution
        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            solution[i] = mat[i][n];
            for (int j = i + 1; j < n; j++) {
                solution[i] -= mat[i][j] * solution[j];
            }
            solution[i] /= mat[i][i];
        }

        // Map variables to values
        Map<String, Double> result = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            result.put(variables.get(i), solution[i]);
        }

        return result;
    }
}
