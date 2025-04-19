package calculator.linear;

import calculator.Expression;
import visitor.EquationCollectorVisitor;
import visitor.Visitor;

import java.util.*;

/**
 * Represents a system of linear equations as an Expression.
 * Supports evaluation using a visitor and solving via the LinearEquationSolver.
 */
public class LinearEquationSystemExpression implements Expression {

    private final List<EquationExpression> equationsSystem;

    /**
     * Constructs a LinearEquationSystemExpression with a list of equations.
     *
     * @param equationsSystem the list of equation expressions
     */
    public LinearEquationSystemExpression(List<EquationExpression> equationsSystem) {
        this.equationsSystem = equationsSystem;
    }


    /**
     * Returns the list of equations in the system.
     *
     * @return list of EquationExpression objects
     */
    public List<EquationExpression> getSystem() {
        return equationsSystem;
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
     * Solves the linear system using matrix operations and returns the result.
     *
     * @return the solution system
     */
    public LinearEquationSystemExpression solve(){

        EquationCollectorVisitor collector = new EquationCollectorVisitor();

        this.accept(collector);

        return LinearEquationSolver.solve(collector.getEquationSystemAsMatrix(), collector.getEquationEqualsValuesAsMatrix(), collector.getAllVariables());
    }

    /**
     * Returns a string representation of the system.
     *
     * @return the system as a formatted string
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        int num = 0;
        for (EquationExpression equation : equationsSystem) {
            sb.append(equation.toString());
            if(num < equationsSystem.size() - 1){
                sb.append(", ");
            }
            num++;
        }
        sb.append(")");
        return sb.toString();
    }


}
