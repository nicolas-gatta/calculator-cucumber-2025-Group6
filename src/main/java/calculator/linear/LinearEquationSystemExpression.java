package calculator.linear;

import calculator.Expression;
import visitor.EquationCollectorVisitor;
import visitor.Visitor;

import java.util.*;

public class LinearEquationSystemExpression implements Expression {

    private final List<EquationExpression> equationsSystem;

    public LinearEquationSystemExpression(List<EquationExpression> equationsSystem) {
        this.equationsSystem = equationsSystem;
    }

    public List<EquationExpression> getSystem() {
        return equationsSystem;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Expression solve(){
        EquationCollectorVisitor collector = new EquationCollectorVisitor();
        this.accept(collector);
        return null;
    }

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
        sb.append(")").append("\n");
        return sb.toString();
    }


}
