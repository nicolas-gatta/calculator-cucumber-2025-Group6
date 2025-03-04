package visitor;

import calculator.MyNumber;
import calculator.Operation;
import calculator.RealNumber;

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

    /**
     * Gets the scientific notation representation
     * @return The number in scientific notation
     */
    public String getResult() {
        return result;
    }
}
