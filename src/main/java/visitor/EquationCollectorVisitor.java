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

    private final Set<String> allVariables = new TreeSet<>();
    private final List<Expression> equationValues = new ArrayList<>();

    private Dictionary<String, MyNumber> storeVariables = new Hashtable<>();

    private String previousSymbol = "";

    private String currentSymbol = "";

    public EquationCollectorVisitor() {
    }

    @Override
    public void visit(MyNumber n) {
        equationValues.add(n);
    }

    @Override
    public void visit(Operation o) {
        if(o.countNbs() >= 2){
            if(Objects.equals(o.getSymbol(), "+")){
                o.getArgs().get(0).accept(this);
                o.getArgs().get(1).accept(this);
            } else if (Objects.equals(o.getSymbol(), "-")) {
                if (o.getArgs().get(1) instanceof Operation) {
                    previousSymbol = "-";
                    o.getArgs().get(1).accept(this);
                    previousSymbol = "";
                }else{
                    currentSymbol = "-";
                    o.getArgs().get(1).accept(this);
                    currentSymbol = "";
                }
            }
        }else{
            o.getArgs().get(0).accept(this);
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
        allVariables.add(v.getRight());
        MyNumber value = currentSymbol == "-" ? new MyNumber(v.getLeft().getValue() * -1) : v.getLeft();
        value = previousSymbol == "-" ? new MyNumber(value.getValue() * -1) : value;
        storeVariables.put(v.getRight(), value);
    }

    @Override
    public void visit(EquationExpression eq) {
        eq.getEquation().getLeft().accept(this);
        eq.getEquation().getRight().accept(this);
    }

    public void visit(LinearEquationSystemExpression l){

        List<Dictionary<String, MyNumber>> variablesValues = new ArrayList<>();
        List<List<MyNumber>> matrixValues = new ArrayList<>();

        for (EquationExpression equation : l.getSystem()) {
            equation.accept(this);
            variablesValues.add(storeVariables);
            storeVariables = new Hashtable<>();
        }

        for (Dictionary<String, MyNumber> dict : variablesValues) {
            List<MyNumber> row = new ArrayList<>();
            for (String var : allVariables) {
                if (dict.get(var) == null) {
                    row.add(new MyNumber(0));
                }else{
                    row.add(dict.get(var));
                }
            }
            matrixValues.add(row);
        }

        System.out.println(matrixValues);
        System.out.println(allVariables);
        System.out.println(equationValues);
    }
}
