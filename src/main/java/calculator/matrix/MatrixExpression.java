package calculator.matrix;

import calculator.Expression;
import visitor.Visitor;

/**
 * Represents a matrix as an {@link Expression} that can be visited by a {@link Visitor}.
 */
public class MatrixExpression implements Expression {
    private final Matrix matrix;

    /**
     * Constructs a new MatrixExpression with the given matrix.
     *
     * @param matrix the matrix wrapped by this expression
     */
    public MatrixExpression(Matrix matrix) {
        this.matrix = matrix;
    }

    /**
     * Returns the matrix held by this expression.
     *
     * @return the internal {@link Matrix}
     */
    public Matrix getMatrix() {
        return matrix;
    }


    /**
     * Accepts a visitor to operate on this matrix expression.
     *
     * @param v the {@link Visitor} to apply
     */
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    /**
     * Returns a string representation of the matrix expression.
     *
     * @return the string representation of the matrix
     */
    @Override
    public String toString() {
        return matrix.toString();
    }


    /**
     * Checks equality with another object.
     *
     * @param o the object to compare with
     * @return {@code true} if equal, {@code false} otherwise
     */
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

    /**
     * Returns the hash code of the matrix expression.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return matrix.toString().hashCode();
    }
}
