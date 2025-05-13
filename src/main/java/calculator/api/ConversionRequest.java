package calculator.api;

/**
 * Represents a request for a unit conversion operation.
 * <p>
 * This class encapsulates all required inputs for a unit conversion,
 * such as the conversion type, source unit, target unit, and the value to convert.
 * </p>
 *
 * Example JSON:
 * <pre>{@code
 * {
 *   "conversionType": "LENGTH",
 *   "fromUnit": "m",
 *   "toUnit": "km",
 *   "value": "1234.5"
 * }
 * }</pre>
 */
public class ConversionRequest {
    /** Type of conversion (e.g., LENGTH, TEMPERATURE, VOLUME). */
    private String conversionType;
    /** Unit to convert from (e.g., "METER"). */
    private String fromUnit;
    /** Unit to convert to (e.g., "KILOMETER"). */
    private String toUnit;
    /** String representation of the numeric value to convert. */
    private String value;

    /**
     * Default constructor.
     * <p>
     * Required for frameworks such as Spring when deserializing from JSON.
     * </p>
     */
    public ConversionRequest() {
        // empty constructor
    }

    /**
     * Gets the conversion type.
     * 
     * @return the conversion type
     */
    public String getConversionType() {
        return conversionType;
    }

    /**
     * Sets the conversion type.
     * 
     * @param conversionType the conversion type to set
     */
    public void setConversionType(String conversionType) {
        this.conversionType = conversionType;
    }

    /**
     * Gets the unit to convert from.
     * 
     * @return the unit to convert from
     */
    public String getFromUnit() {
        return fromUnit;
    }

    /**
     * Sets the unit to convert from.
     * 
     * @param fromUnit the unit to convert from
     */
    public void setFromUnit(String fromUnit) {
        this.fromUnit = fromUnit;
    }

    /**
     * Gets the target unit to convert to.
     * 
     * @return the source unit
     */
    public String getToUnit() {
        return toUnit;
    }

    /**
     * Sets the target unit to convert to.
     * 
     * @param toUnit the target unit to set
     */
    public void setToUnit(String toUnit) {
        this.toUnit = toUnit;
    }

    /**
     * Gets the value to convert.
     * 
     * @return the value to convert
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value to convert.
     * 
     * @param value the value to convert
     */
    public void setValue(String value) {
        this.value = value;
    }
}
