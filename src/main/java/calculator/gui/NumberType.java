package calculator.gui;

/**
 * Enum representing the different number types supported by the calculator.
 * Each type has a display name that is shown in the UI.
 */
public enum NumberType {
    /**
     * Represents integer numbers (whole numbers without fractional parts).
     */
    INTEGER("Integer"),
    
    /**
     * Represents rational numbers (fractions with numerator and denominator).
     */
    RATIONAL("Rational"),
    
    /**
     * Represents real numbers (numbers with decimal points).
     */
    REAL("Real"),
    
    /**
     * Represents complex numbers (numbers with real and imaginary parts).
     */
    COMPLEX("Complex"),
    
    /**
     * Represents linear equation systems.
     */
    LINEAR_SYSTEM("Linear System"),
    
    /**
     * Represents matrices for matrix operations.
     */
    MATRIX("Matrix");
    
    private final String displayName;
    
    /**
     * Constructs a NumberType with the specified display name.
     *
     * @param displayName The name to display in the UI
     */
    NumberType(String displayName) {
        this.displayName = displayName;
    }
    
    /**
     * Returns the display name of the number type.
     *
     * @return The display name
     */
    @Override
    public String toString() {
        return displayName;
    }
}