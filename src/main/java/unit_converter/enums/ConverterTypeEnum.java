package unit_converter.enums;

import java.util.Optional;

/**
 * Enumeration of all available converter types in the application.
 * <p>
 * Each constant represents a category of physical or logical units
 * that can be converted (e.g., LENGTH, ENERGY, BINARY).
 * </p>
 *
 * This enum is primarily used to route requests to the correct
 * {@link unit_converter.IUnitConverter} via the {@code ConverterFactory}.
 */
public enum ConverterTypeEnum {
    /**
     * Length unit conversions (e.g., meters, feet, inches).
     */
    LENGTH,

    /**
     * Volume unit conversions (e.g., liters, gallons).
     */
    VOLUME,

    /**
     * Temperature unit conversions (e.g., Celsius, Fahrenheit).
     */
    TEMPERATURE,

    /**
     * Area unit conversions (e.g., square meters, acres).
     */
    AREA,

    /**
     * Density unit conversions (e.g., kg/m³, g/cm³).
     */
    DENSITY,

    /**
     * Currency conversions (e.g., USD, EUR).
     */
    CURRENCY,

    /**
     * Pressure unit conversions (e.g., Pascal, bar).
     */
    PRESSURE,

    /**
     * Speed unit conversions (e.g., km/h, mph).
     */
    SPEED,

    /**
     * Energy unit conversions (e.g., Joules, calories).
     */
    ENERGY,

    /**
     * Force unit conversions (e.g., Newton, pound-force).
     */
    FORCE,

    /**
     * Time unit conversions (e.g., seconds, minutes, hours).
     */
    TIME,

    /**
     * Numeral unit conversions (e.g., binary, octal, hexadecimal).
     */
    BINARY;

    /**
     * Returns an optional enum value matching the given string.
     * <p>
     * The lookup is case-insensitive and safely wrapped in an {@link Optional}.
     * </p>
     *
     * @param str the string to match (e.g., "length", "ENERGY")
     * @return an Optional containing the matched enum, or empty if not found
     */
    public static Optional<ConverterTypeEnum> fromString(String str){
        if(str == null) return Optional.empty();
        try {
            return Optional.of(ConverterTypeEnum.valueOf(str.trim().toUpperCase()));
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }
}
