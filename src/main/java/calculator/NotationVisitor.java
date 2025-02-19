package calculator;

import java.util.stream.Stream;

public class NotationVisitor implements ExpressionVisitor {

    @Override
    public String visitPrefix(Operation operation) {
        Stream<String> s = operation.getArgs().stream().map(Expression::toString);
        return operation.getSymbol() + " (" + s.reduce((s1, s2) -> s1 + ", " + s2).get() + ")";
    }

    @Override
    public String visitInfix(Operation operation) {
        Stream<String> s = operation.getArgs().stream().map(Expression::toString);
        return "( " + s.reduce((s1, s2) -> s1 + " " + operation.getSymbol() + " " + s2).get() + " )";
    }

    @Override
    public String visitPostfix(Operation operation) {
        Stream<String> s = operation.getArgs().stream().map(Expression::toString);
        return "(" + s.reduce((s1, s2) -> s1 + ", " + s2).get() + ") " + operation.getSymbol();
    }
}
