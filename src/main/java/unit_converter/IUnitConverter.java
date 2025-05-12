package unit_converter;

/**
 * Interface for unit converters.
 * Provides methods to convert values between different units of measurement.
 *
 * @param <T> the type of value to convert (typically Double for numerical conversions or String for text-based conversions)
 */
public interface IUnitConverter<T>{
    /**
     * Converts a value from one unit to another.
     *
     * @param fromUnit the source unit as a string
     * @param toUnit the target unit as a string
     * @param value the value to convert
     * @return the converted value
     */
    T convert(String fromUnit, String toUnit, T value);
    //T convert(U from, U to, T value);
}
