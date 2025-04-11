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
    private final List<MyNumber> equationValues = new ArrayList<>();

    public Set<String> getVariables() {
        return variables;
    }

    public List<MyNumber> getEquationValues() {
        return equationValues;
    }

    @Override
    public void visit(MyNumber n) {

    }

    @Override
    public void visit(Operation o) {
        for (Expression e : o.getArgs()){
            e.accept(this);
        }
    }

    @Override
    public void visit(RealNumber n) {

    }

    @Override
    public void visit(ComplexNumber n) {

    }

    @Override
    public void visit(RationalNumber n) {

    }

    @Override
    public void visit(MatrixExpression n) {

    }

    @Override
    public void visit(VariableExpression n) {
        variables.add(n.getRight());
    }

    @Override
    public void visit(EquationExpression e) {
        equationValues.add((MyNumber) e.getEquation().getRight());
    }
}
