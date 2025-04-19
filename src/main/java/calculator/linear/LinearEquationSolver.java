package calculator.linear;

import calculator.matrix.Matrix;
import calculator.numbers.RealNumber;

import java.util.*;

public class LinearEquationSolver {

    public static LinearEquationSystemExpression solve(Matrix A, Matrix b, List<String> variables) {

        if (A.determinant() == 0) {
            throw new IllegalArgumentException("No unique solution: determinant is 0.");
        }

        List<EquationExpression> equations = new ArrayList<>();

        Matrix invertedMatrix = A.inverse();

        Matrix result = invertedMatrix.multiply(b);

        System.out.println(A);
        System.out.println(invertedMatrix);
        System.out.println(b);
        System.out.println(variables);

        for (int i = 0; i < variables.size(); i++) {

            Equation equation = new Equation(new VariableExpression(variables.get(i)), new RealNumber(result.getValue(i, 0)));
            EquationExpression equationExpression = new EquationExpression(equation);
            equations.add(equationExpression);
        }

        return new LinearEquationSystemExpression(equations);
    }
}
