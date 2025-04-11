package calculator.linear;

import visitor.EquationCollectorVisitor;

import java.util.*;

public class LinearEquationSystem {

    private final List<EquationExpression> equationsSystem;

    public LinearEquationSystem(List<EquationExpression> equationsSystem) {
        this.equationsSystem = equationsSystem;
    }

    public List<EquationExpression> getSystem() {
        return equationsSystem;
    }

    public void getNecessaryValuesForSolving(){
        EquationCollectorVisitor collector = new EquationCollectorVisitor();
        for (EquationExpression equation : equationsSystem) {
            equation.accept(collector);
        }
        System.out.println(collector.getVariables());
        System.out.println(collector.getEquationValues());
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (EquationExpression equation : equationsSystem) {
            sb.append("- ").append(equation.toString()).append("\n");
        }
        return sb.toString();
    }
}
