package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.Notation;
import calculator.Operation;

import java.util.stream.Stream;

/**
 * A visitor that converts arithmetic operations into different notations.
 * Implements the ExpressionVisitor interface.
 */
public class NotationVisitor extends Visitor {

    private final Notation notation;
    private String result = "";

    public NotationVisitor(Notation n){
        this.notation = n;
    }

    public void visit(MyNumber n){
        this.result = n.toString();
    }

    public void visit(Operation o) {
        String arguments = o.getArgs().stream()
                .map(arg -> {
                    NotationVisitor visitor = new NotationVisitor(this.notation);
                    arg.accept(visitor);
                    return visitor.getResult();
                })
                .reduce((a1, a2) -> a1 + " " + o.getSymbol() + " " + a2)
                .orElse("");

        switch (notation) {
            case INFIX:
                this.result = "( " + arguments + " )";
                break;
            case PREFIX:
                this.result = o.getSymbol() + " ( " + arguments + " )";
                break;
            case POSTFIX:
                this.result = "( " + arguments + " ) " + o.getSymbol();
                break;
        }
    }

    public String getResult(){
        return this.result;
    }
}
