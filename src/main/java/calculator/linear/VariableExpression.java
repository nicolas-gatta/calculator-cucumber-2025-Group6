package calculator.linear;

import calculator.Expression;
import calculator.numbers.MyNumber;
import visitor.Visitor;

import java.util.Objects;

/**
 * Represents a variable term in an equation, such as "3x" or "x".
 */
public class VariableExpression implements Expression {

    private final MyNumber left;
    private final String right;

    /**
     * Constructs a VariableExpression with a coefficient and variable name.
     *
     * @param left  the numeric coefficient
     * @param right the variable name
     */
    public VariableExpression(MyNumber left, String right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Constructs a VariableExpression with a coefficient of 1.
     *
     * @param right the variable name
     */
    public VariableExpression(String right) {
        this.left = new MyNumber(1);
        this.right = right;
    }

    /**
     * Returns the coefficient of the variable.
     *
     * @return the numeric coefficient
     */
    public MyNumber getLeft() {
        return left;
    }

    /**
     * Returns the name of the variable.
     *
     * @return the variable name
     */
    public String getRight() {
        return right;
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
     * Returns the string representation of the variable expression.
     *
     * @return the expression as a string
     */
    @Override
    public String toString() {
        return  left.getValue() != 1 ? left + right : right;
    }

    /**
     * Checks equality with another object.
     *
     * @param obj the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        VariableExpression other = (VariableExpression) obj;
        // Compare numerators and denominators after simplification
        return this.left == other.left && Objects.equals(this.right, other.right);
    }

    /**
     * Returns the hash code of the expression.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return left.hashCode() + right.hashCode();
    }
}


