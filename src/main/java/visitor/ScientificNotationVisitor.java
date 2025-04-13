package visitor;

import calculator.numbers.MyNumber;
import calculator.operations.Operation;
import calculator.numbers.RealNumber;   
import calculator.numbers.ComplexNumber;
import calculator.numbers.RationalNumber;
import calculator.matrix.MatrixExpression;
import java.util.Locale;

/**
 * Visitor that converts numbers to scientific notation
 */
public class ScientificNotationVisitor extends Visitor {
    private String result;
    private int precision;
    private static final Locale US_LOCALE = Locale.US; // Force US locale for tests

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
        result = String.format(US_LOCALE, "%." + precision + "E", (double)n.getValue());
    }

    @Override
    public void visit(Operation o) {
        // Not applicable for operations
        result = o.toString();
    }

    @Override
    public void visit(RealNumber n) {
        result = String.format(US_LOCALE, "%." + precision + "E", n.getValue());
    }

    @Override
    public void visit(ComplexNumber n) {
        double realPart = n.getReal();
        double imaginaryPart = n.getImaginary();
        result = String.format(US_LOCALE, "%." + precision + "E + %." + precision + "Ei", realPart, imaginaryPart);
    }

    @Override
    public void visit(RationalNumber n) {
        result = String.format(US_LOCALE, "%." + precision + "E", (double)n.getNumerator() / n.getDenominator());
    }

    @Override
    public void visit(MatrixExpression m) {
        result = m.toString();
    }

    /**
     * Gets the scientific notation representation
     * @return The number in scientific notation
     */
    public String getResult() {
        return result;
    }
}
