package unit_converter;

import java.util.List;

/**
 * Generic interface for unit converters.
 * <p>
 * This interface defines the basic contract for converting values from one unit to another.
 * It is designed to be implemented by converters handling specific measurement types
 * such as length, area, volume, currency, etc.
 * </p>
 *
 * @param <T> the type of value to convert (e.g., {@code Double}, {@code String})
 */
public interface IUnitConverter<T>{

    /**
     * Converts a value from one unit to another.
     *
     * @param fromUnit the name of the source unit (e.g., "meter")
     * @param toUnit   the name of the target unit (e.g., "kilometer")
     * @param value    the value to convert
     * @return the converted value in the target unit
     * @throws IllegalArgumentException if the units are invalid or the conversion fails
     */
    T convert(String fromUnit, String toUnit, T value);

    /**
     * Returns a list of user-friendly unit names supported by this converter.
     * <p>
     * These names are typically used for display purposes in user interfaces or CLI,
     * and may differ from enum names or internal identifiers.
     * </p>
     *
     * @return a list of supported unit names for this converter
     */
    List<String> getConverterUnitListNames();

    /**
     * Returns the class type associated with this converter's value type.
     * <p>
     * This method is essential in dynamic contexts (such as CLI) to determine the
     * appropriate way to parse and handle input values.
     * </p>
     *
     * @return the class of the type {@code T} (e.g., {@code Double.class})
     */
    Class<T> getValueType(); //Need this to specify to java which type as to be cast for CLI

}
