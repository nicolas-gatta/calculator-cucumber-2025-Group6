package calculator.numbers;

import calculator.Expression;
import visitor.Visitor;

/**
 * RealNumber represents floating point numbers in the calculator.
 * It supports scientific notation and configurable precision.
 */
public class RealNumber implements Expression {
    private final double value;
    private int precision;

    /**
     * Constructs a new RealNumber with a specific value and precision
     * @param value The double value to store
     * @param precision The number of significant digits to display
     */
    public RealNumber(double value, int precision) {
        if (precision <= 0) {
            throw new IllegalArgumentException("Precision must be positive");
        }
        this.value = value;
        this.precision = precision;
    }

    /**
     * Gets the stored double value
     * @return The double value
     */
    public double getValue() {
        return value;
    }

    /**
     * Gets the current precision setting
     * @return The number of significant digits
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * Sets the precision for displaying this number
     * @param precision The number of significant digits to display
     */
    public void setPrecision(int precision) {
        if (precision <= 0) {
            throw new IllegalArgumentException("Precision must be positive");
        }
        this.precision = precision;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("%." + precision + "f", value);
    }
}
