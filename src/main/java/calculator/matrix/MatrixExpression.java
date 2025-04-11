package calculator.matrix;

import calculator.Expression;
import visitor.Visitor;

public class MatrixExpression implements Expression {
    private final Matrix matrix;

    public MatrixExpression(Matrix matrix) {
        this.matrix = matrix;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return matrix.toString();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o){
            return true;
        }

        if (!(o instanceof MatrixExpression)){
            return false;
        }

        return matrix.toString().equals(((MatrixExpression) o).matrix.toString());
    }

    @Override
    public int hashCode() {
        return matrix.toString().hashCode();
    }
}
