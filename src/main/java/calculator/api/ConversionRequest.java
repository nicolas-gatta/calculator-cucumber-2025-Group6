package calculator.api;

/**
 * Data Transfer Object (DTO) representing a unit conversion request.
 * <p>
 * This class is used to receive JSON data from API clients for unit conversion operations.
 * It contains the type of conversion, the source and target units, and the numeric value to convert.
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

    /** Empty constructor required for deserialization. */
    public ConversionRequest() {
        //Empty constructor
    }

    /** @return the conversion type */
    public String getConversionType() { return conversionType; }
    /** Set the conversion type
     *
     * @param conversionType the conversion type to set */
    public void setConversionType(String conversionType) { this.conversionType = conversionType; }

    /** @return the unit to convert from */
    public String getFromUnit() { return fromUnit; }
    /** Set the unit to convert from
     *
     * @param fromUnit the unit to convert from
     */
    public void setFromUnit(String fromUnit) { this.fromUnit = fromUnit; }

    /** @return the source unit */
    public String getToUnit() { return toUnit; }
    /** Set the unit to convert to
     *
     * @param toUnit the target unit to set */
    public void setToUnit(String toUnit) { this.toUnit = toUnit; }

    /** @return the value to convert */
    public String getValue() { return value; }
    /** Set the value to convert
     *
     * @param value the value to convert */
    public void setValue(String value) { this.value = value; }
}
