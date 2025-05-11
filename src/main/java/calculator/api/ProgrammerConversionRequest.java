package calculator.api;

/**
 * Represents a request payload for converting numbers between different number systems
 * in the programmer calculator (e.g., binary, decimal, hexadecimal, octal).
 */
public class ProgrammerConversionRequest {
    /** The source number system (e.g., "binary", "decimal", "hex"). */
    private String fromUnit;
    /** The target number system to convert to (e.g., "hex", "decimal", "octal"). */
    private String toUnit;
    /** The value to be converted, represented as a string. */
    private String value;

    /**
     * Default constructor.
     */
    public ProgrammerConversionRequest() {
        // Empty constructor
    }

    /**
     * Gets the source number system.
     *
     * @return the source number system
     */
    public String getFromUnit() { return fromUnit; }
    /**
     * Sets the source number system.
     *
     * @param fromUnit the source number system
     */
    public void setFromUnit(String fromUnit) { this.fromUnit = fromUnit; }

    /**
     * Gets the target number system.
     *
     * @return the target number system
     */
    public String getToUnit() { return toUnit; }
    /**
     * Sets the target number system.
     *
     * @param toUnit the target number system
     */
    public void setToUnit(String toUnit) { this.toUnit = toUnit; }

    /**
     * Gets the value to convert.
     *
     * @return the value to convert
     */
    public String getValue() { return value; }
    /**
     * Sets the value to convert.
     *
     * @param value the value to convert
     */
    public void setValue(String value) { this.value = value; }
}
