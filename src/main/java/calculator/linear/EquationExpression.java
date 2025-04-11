package calculator.linear;

import calculator.Expression;
import calculator.operations.Operation;
import visitor.Visitor;

public class EquationExpression implements Expression {

    private final Equation equation;

    public EquationExpression(Equation equation) {
        this.equation = equation;
    }

    public Equation getEquation() {
        return equation;
    }

    @Override
    public void accept(Visitor v) {
        equation.getLeft().accept(v);
        equation.getRight().accept(v);
    }

    @Override
    public String toString() {
        return equation.toString();
    }

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

    @Override
    public int hashCode() {
        return equation.hashCode();
    }
}
