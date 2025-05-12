package unit_converter.enums;

import java.util.Optional;

/**
 * Enum representing the different types of unit conversions supported by the application.
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
    TIME;

    /**
     * Converts a string representation to the corresponding ConverterTypeEnum value.
     *
     * @param str The string to convert to a ConverterTypeEnum
     * @return An Optional containing the matching ConverterTypeEnum if found, or an empty Optional if no match
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
