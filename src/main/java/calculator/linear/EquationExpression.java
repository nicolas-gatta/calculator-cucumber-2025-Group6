package calculator.linear;

import calculator.Expression;
import calculator.operations.Operation;
import visitor.Visitor;

/**
 * Wraps an Equation object into an Expression to be processed
 * within a visitor-based evaluation system.
 */
public class EquationExpression implements Expression {

    private final Equation equation;

    /**
     * Constructs an EquationExpression with the given equation.
     *
     * @param equation the equation to wrap
     */
    public EquationExpression(Equation equation) {
        this.equation = equation;
    }

    /**
     * Returns the underlying equation.
     *
     * @return the equation
     */
    public Equation getEquation() {
        return equation;
    }

    /**
     * Accepts a visitor.
     *
     * @param v the visitor to accept
     */
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    /**
     * Returns the string representation of the wrapped equation.
     *
     * @return the equation as a string
     */
    @Override
    public String toString() {
        return equation.toString();
    }

    /**
     * Checks equality based on the string representation of the equation.
     *
     * @param o the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof EquationExpression)) {
            return false;
        }

        return equation.toString().equals(((EquationExpression) o).equation.toString());
    }

    /**
     * Returns the hash code of the equation.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return equation.hashCode();
    }
}
