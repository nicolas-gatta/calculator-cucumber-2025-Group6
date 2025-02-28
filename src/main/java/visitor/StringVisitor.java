package visitor;

import calculator.Operation;
import calculator.MyNumber;
import calculator.Notation;

import java.util.stream.Stream;

public class StringVisitor extends Visitor {
    private final Notation notation;
    private String result;

    public StringVisitor(Notation notation) {
        this.notation = notation;
        this.result = "";
    }

    @Override
    public void visit(MyNumber n) {
        result = n.getValue().toString();
    }

    @Override
    public void visit(Operation o) {
        Stream<String> s = o.getArgs().stream().map(expr -> {
            StringVisitor sv = new StringVisitor(notation);
            expr.accept(sv);
            return sv.getResult();
        });

        result = switch (notation) {
            case INFIX -> "( " + 
                s.reduce((s1, s2) -> s1 + " " + o.getSymbol() + " " + s2).get() +
                " )";
            case PREFIX -> o.getSymbol() + " (" + 
                s.reduce((s1, s2) -> s1 + ", " + s2).get() +
                ")";
            case POSTFIX -> "(" + 
                s.reduce((s1, s2) -> s1 + ", " + s2).get() +
                ") " + o.getSymbol();
        };
    }

    public String getResult() {
        return result;
    }


        
}
