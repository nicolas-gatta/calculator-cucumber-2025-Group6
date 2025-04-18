package visitor;

import calculator.Expression;
import calculator.linear.EquationExpression;
import calculator.linear.LinearEquationSystemExpression;
import calculator.linear.VariableExpression;
import calculator.matrix.MatrixExpression;
import calculator.numbers.ComplexNumber;
import calculator.numbers.MyNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.operations.Operation;
import java.util.Dictionary;
import java.util.Hashtable;


import java.util.*;

public class EquationCollectorVisitor extends Visitor {

    private final Set<String> variables = new HashSet<>();
    private final List<Expression> equationValues = new ArrayList<>();

    private Dictionary<String, MyNumber> store_variables = new Hashtable<>();

    private final List<Dictionary<String, MyNumber>> variables_values = new ArrayList<>();

    private String previous_symbol = "";

    public EquationCollectorVisitor() {
    }

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
        System.out.println(o.getSymbol());
        System.out.println(o.getArgs());
        o.getArgs().get(0).accept(this);
        if(o.countNbs() == 2){
            if (o.getSymbol() == "-") {
                if (o.getArgs().get(1) instanceof Operation) {
                    previous_symbol = o.getSymbol();
                }else{

                }
                o.getArgs().get(1).accept(this);
            }else{
                o.getArgs().get(1).accept(this);
            }
        }
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
        variables.add(v.getRight());
        store_variables.put(v.getRight(), v.getLeft());
    }

    @Override
    public void visit(EquationExpression eq) {
        // Collect variables from left
        eq.getEquation().getLeft().accept(this);

        // Collect expression value from right
        eq.getEquation().getRight().accept(this);
    }

    public void visit(LinearEquationSystemExpression l){

        for (EquationExpression equation : l.getSystem()) {
            equation.accept(this);
            variables_values.add(store_variables);
            store_variables = new Hashtable<>();
        }

        System.out.println(variables_values);
        System.out.println(this.getVariables());
        System.out.println(this.getEquationValues());
    }
}
