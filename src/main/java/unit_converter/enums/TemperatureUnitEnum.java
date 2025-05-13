package unit_converter.enums;

/**
 * Enumeration of temperature units supported by the temperature converter.
 * <p>
 * This enum defines three standard units used in temperature measurement:
 * Celsius, Fahrenheit, and Kelvin. It is used to drive logic in the
 * {@code TemperatureConverter}, which relies on unit-specific formulas rather
 * than scalar conversion factors.
 * </p>
 *
 * <p>Supported units:</p>
 * <ul>
 *   <li>{@code CELSIUS} – °C</li>
 *   <li>{@code FAHRENHEIT} – °F</li>
 *   <li>{@code KELVIN} – K</li>
 * </ul>
 */
public enum TemperatureUnitEnum {
    /** Represents the Celsius temperature scale */
    CELSIUS,
    /** Represents the Fahrenheit temperature scale */
    FAHRENHEIT,
    /** Represents the Kelvin temperature scale */
    KELVIN;

    /**
     * Parses a string into a {@code TemperatureUnitEnum}.
     * <p>
     * Matching is case-insensitive and accepts names with or without spaces.
     * </p>
     *
     * @param str the string to parse (e.g., "celsius", "KELVIN", "fahrenheit")
     * @return the corresponding {@code TemperatureUnitEnum}
     * @throws IllegalArgumentException if no matching unit is found
     */
    public static TemperatureUnitEnum fromString(String str){
        return TemperatureUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }

}
