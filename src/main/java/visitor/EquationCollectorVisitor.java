package visitor;

import calculator.linear.EquationExpression;
import calculator.linear.LinearEquationSystemExpression;
import calculator.linear.VariableExpression;
import calculator.matrix.Matrix;
import calculator.matrix.MatrixExpression;
import calculator.numbers.ComplexNumber;
import calculator.numbers.MyNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.operations.Operation;

import java.util.Dictionary;
import java.util.Hashtable;


import java.util.*;

/**
 * EquationCollectorVisitor is a visitor implementation that collects information from
 * linear equations and systems of linear equations, transforming them into matrix form.
 * It supports the extraction of variable coefficients, equation results, and generates
 * matrices representing the linear system for further processing.
 */
public class EquationCollectorVisitor extends Visitor {

    private final Set<String> allVariables = new TreeSet<>();
    private final List<Double> equationEqualsValues = new ArrayList<>();
    private Dictionary<String, Double> storeVariables = new Hashtable<>();
    private List<List<Double>> equationsValues = new ArrayList<>();

    private String previousSymbol = "";

    private String currentSymbol = "";
    
    /**
     * Default constructor for EquationCollectorVisitor.
     * Initializes the visitor with empty collections for storing variables and equation values.
     */
    public EquationCollectorVisitor() {
        // Initialize with default empty collections
    }

    /**
     * Returns a list of all variables in the system.
     *
     * @return a list of all variable names.
     */
    public List<String> getAllVariables() {
        return new ArrayList<>(allVariables);
    }


    /**
     * Build a matrix representing the right-hand values of the equations.
     *
     * @return a Matrix containing the right-hand side values.
     */
    public Matrix getEquationEqualsValuesAsMatrix() {
        int rows = equationEqualsValues.size();
        int cols = 1;
        double[][] data = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            data[i][0] = equationEqualsValues.get(i);
        }
        return new Matrix(data);
    }

    /**
     * Build a matrix representing the left-hand values of the equations.
     *
     * @return a Matrix containing the left-hand side values.
     */
    public Matrix getEquationSystemAsMatrix() {
        int rows = equationsValues.size();
        int cols = equationsValues.get(0).size();
        double[][] data = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = equationsValues.get(i).get(j);
            }
        }
        return new Matrix(data);
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
    public void visit(MyNumber n) {
        equationEqualsValues.add(n.getValue().doubleValue());
    }

    @Override
    public void visit(RealNumber n) {
        equationEqualsValues.add(n.getValue());
    }

    @Override
    public void visit(ComplexNumber n) {
        throw new IllegalArgumentException("Linear Equation does not support Complex Number");
    }

    @Override
    public void visit(RationalNumber n) {
        equationEqualsValues.add(n.getValue());
    }

    @Override
    public void visit(MatrixExpression n) {
        throw new IllegalArgumentException("Linear Equation does not support matrix operations");
    }

    @Override
    public void visit(VariableExpression v) {
        allVariables.add(v.getRight());
        double value = Objects.equals(currentSymbol, "-") ? v.getLeft().getValue() * -1 : v.getLeft().getValue();
        value = Objects.equals(previousSymbol, "-") ? value * -1 : value;
        storeVariables.put(v.getRight(), value);
    }

    @Override
    public void visit(EquationExpression eq) {
        eq.getEquation().getLeft().accept(this);
        eq.getEquation().getRight().accept(this);
    }


    @Override
    public void visit(LinearEquationSystemExpression l){

        List<Dictionary<String, Double>> variablesValues = new ArrayList<>();

        for (EquationExpression equation : l.getSystem()) {
            equation.accept(this);
            variablesValues.add(storeVariables);
            storeVariables = new Hashtable<>();
        }

        for (Dictionary<String, Double> dict : variablesValues) {
            List<Double> row = new ArrayList<>();
            for (String var : allVariables) {
                if (dict.get(var) == null) {
                    row.add(0.0);
                }else{
                    row.add(dict.get(var));
                }
            }
            equationsValues.add(row);
        }
    }
}
