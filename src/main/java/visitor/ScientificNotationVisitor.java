package visitor;

import calculator.linear.EquationExpression;
import calculator.linear.LinearEquationSystemExpression;
import calculator.linear.VariableExpression;
import calculator.numbers.MyNumber;
import calculator.operations.Operation;
import calculator.numbers.RealNumber;   
import calculator.numbers.ComplexNumber;
import calculator.numbers.RationalNumber;
import calculator.matrix.MatrixExpression;

/**
 * Visitor that converts numbers to scientific notation
 */
public class ScientificNotationVisitor extends Visitor {
    private String result;
    private int precision;

    /**
     * Creates a new ScientificNotationVisitor with default precision
     */
    public ScientificNotationVisitor() {
        this.precision = 6; // default precision
    }

    /**
     * Creates a new ScientificNotationVisitor with specified precision
     * @param precision The number of significant digits to display
     */
    public ScientificNotationVisitor(int precision) {
        this.precision = precision;
    }

    @Override
    public void visit(MyNumber n) {
        result = String.format("%." + precision + "E", (double)n.getValue());
    }

    @Override
    public void visit(Operation o) {
        // Not applicable for operations
        result = o.toString();
    }

    @Override
    public void visit(RealNumber n) {
        result = String.format("%." + precision + "E", n.getValue());
    }

    @Override
    public void visit(ComplexNumber n) {
        double realPart = n.getReal();
        double imaginaryPart = n.getImaginary();
        result = String.format("%." + precision + "E + %." + precision + "Ei", realPart, imaginaryPart);
    }

    @Override
    public void visit(RationalNumber n) {
        result = String.format("%." + precision + "E", (double)n.getNumerator() / n.getDenominator());
    }

    @Override
    public void visit(MatrixExpression m) {
        result = m.toString();
    }

    @Override
    public void visit(VariableExpression v) {
        result = v.toString();
    }

    @Override
    public void visit(EquationExpression e) {
        result = e.toString();
    }

    @Override
    public void visit(LinearEquationSystemExpression l) {
        result = l.toString();
    }

    /**
     * Gets the scientific notation representation
     * @return The number in scientific notation
     */
    public String getResult() {
        return result;
    }
}
