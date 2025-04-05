package matrix;

import java.util.*;

import calculator.IllegalConstruction;
import calculator.Operation;
import calculator.Expression;

public class MatrixOperation extends Operation {

    private String symbol;

    public MatrixOperation(List<Expression> elist, String symbol) throws IllegalConstruction {
        super(elist, null);
        this.symbol = symbol;
    }

    @Override
    public int op(int left, int right) {
        throw new UnsupportedOperationException("MatrixOperation doesn't support scalar evaluation.");
    }

    public Matrix compute() {
        if (args.size() < 1)
            throw new IllegalArgumentException("Not enough parameters to do any operation on the matrix");

        Expression first = args.get(0);

        if (!(first instanceof MatrixExpression))
            throw new IllegalArgumentException("The operation can only contain matrix");

        Matrix result = ((MatrixExpression) first).getMatrix();

        for (int i = 1; i < args.size(); i++) {
            if (!(args.get(i) instanceof MatrixExpression))
                throw new IllegalArgumentException("All the parameters should be Matrix type");
            Matrix other = ((MatrixExpression) args.get(i)).getMatrix();

            switch (symbol) {
                case "+" -> result = result.add(other);
                case "-" -> result = result.substract(other);
                case "*" -> result = result.multiply(other);
                default -> throw new UnsupportedOperationException("Unsupported Operation : " + symbol);
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "(" + args.get(0) + " " + symbol + " " + args.get(1) + ")";
    }
}
