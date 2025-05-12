package unit_converter.enums;

/**
 * Enumeration of temperature units.
 * Provides functionality for converting between different temperature units.
 */
public enum TemperatureUnitEnum {
    /** Represents the Celsius temperature scale */
    CELSIUS,
    /** Represents the Fahrenheit temperature scale */
    FAHRENHEIT,
    /** Represents the Kelvin temperature scale */
    KELVIN;

    /**
     * Converts a string representation to the corresponding TemperatureUnitEnum.
     * The string is trimmed, converted to uppercase, and spaces are replaced with underscores.
     * 
     * @param str The string to convert
     * @return The corresponding TemperatureUnitEnum
     * @throws IllegalArgumentException if the string does not match any enum constant
     */
    public static TemperatureUnitEnum fromSting(String str){
        return TemperatureUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }

}
