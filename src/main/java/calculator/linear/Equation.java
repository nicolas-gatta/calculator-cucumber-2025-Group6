package calculator.linear;

import calculator.Expression;

/**
 * Represents a mathematical equation with a left and a right expression.
 */
public class Equation {

    private final Expression left;
    private final Expression right;

    /**
     * Constructs an Equation with a left and right expression.
     * @param left The left-hand side of the equation.
     * @param right The right-hand side of the equation.
     */
    public Equation(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    /**
     * Gets the left-hand side of the equation.
     * @return The left expression.
     */
    public Expression getLeft() {
        return left;
    }

    /**
     * Gets the right-hand side of the equation.
     * @return The right expression.
     */
    public Expression getRight() {
        return right;
    }

    /**
     * Returns a string representation of the equation.
     * @return A string in the form "left = right".
     */
    @Override
    public String toString() {
        return left + " = " + right;
    }
}
