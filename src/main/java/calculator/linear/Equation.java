package calculator.linear;

import calculator.Expression;

public class Equation {

    private final Expression left;
    private final Expression right;

    public Equation(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return left + " = " + right;
    }
}
