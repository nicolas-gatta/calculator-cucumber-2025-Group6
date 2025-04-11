package visitor;

import calculator.Expression;
import calculator.linear.EquationExpression;
import calculator.linear.VariableExpression;
import calculator.matrix.MatrixExpression;
import calculator.numbers.ComplexNumber;
import calculator.numbers.MyNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.operations.Operation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EquationCollectorVisitor extends Visitor {


    private final Set<String> variables = new HashSet<>();
    private final List<Expression> equationValues = new ArrayList<>();

    public Set<String> getVariables() {
        return variables;
    }

    public List<Expression> getEquationValues() {
        return equationValues;
    }

    @Override
    public void visit(MyNumber n) {
        equationValues.add(n);
    }

    @Override
    public void visit(Operation o) {

    }

    @Override
    public void visit(RealNumber n) {
        equationValues.add(n);
    }

    @Override
    public void visit(ComplexNumber n) {
        equationValues.add(n);
    }

    @Override
    public void visit(RationalNumber n) {
        equationValues.add(n);
    }

    @Override
    public void visit(MatrixExpression n) {

    }

    @Override
    public void visit(VariableExpression v) {
        System.out.println(v.toString());
        variables.add(v.getRight());
    }

    @Override
    public void visit(EquationExpression e) {
    }
}
