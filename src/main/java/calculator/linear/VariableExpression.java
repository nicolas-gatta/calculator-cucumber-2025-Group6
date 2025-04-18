package calculator.linear;

import calculator.Expression;
import calculator.numbers.MyNumber;
import visitor.Visitor;

import java.util.Objects;

public class VariableExpression implements Expression {

    private final MyNumber left;
    private final String right;

    public VariableExpression(MyNumber left, String right) {
        this.left = left;
        this.right = right;
    }

    public VariableExpression(String right) {
        this.left = new MyNumber(1);
        this.right = right;
    }

    public MyNumber getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return left + right;
    }

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

    @Override
    public int hashCode() {
        return left.hashCode() + right.hashCode();
    }
}


