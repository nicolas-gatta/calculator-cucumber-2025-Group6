package calculator;

public interface ExpressionVisitor {
    String visitPrefix(Operation operation);
    String visitInfix(Operation operation);
    String visitPostfix(Operation operation);
}
