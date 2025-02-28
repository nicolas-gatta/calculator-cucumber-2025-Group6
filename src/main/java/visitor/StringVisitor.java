package visitor;

import calculator.Operation;
import calculator.MyNumber;
import calculator.Notation;

import java.util.stream.Stream;

/**
 * A visitor that converts an expression tree into its string representation
 * according to a specified notation (infix, prefix, or postfix).
 */
public class StringVisitor extends Visitor {
    private final Notation notation;
    private String result;

    /**
     * Creates a new StringVisitor with the specified notation.
     *
     * @param notation The notation to use for string conversion (INFIX, PREFIX, or POSTFIX)
     */
    public StringVisitor(Notation notation) {
        this.notation = notation;
        this.result = "";
    }

    /**
     * Visits a number node and converts it to its string representation.
     *
     * @param n The number node being visited
     */
    @Override
    public void visit(MyNumber n) {
        result = n.getValue().toString();
    }

    /**
     * Visits an operation node and converts it and its arguments to a string
     * according to the specified notation.
     * - INFIX: ( arg1 op arg2 )
     * - PREFIX: op (arg1, arg2)
     * - POSTFIX: (arg1, arg2) op
     *
     * @param o The operation node being visited
     */
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

    /**
     * Returns the string representation of the last visited expression.
     * This method should be called after visiting an expression to get its string form.
     * @return The string representation of the last visited expression
     */
    public String getResult() {
        return result;
    }
}
