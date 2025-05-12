package unit_converter;

import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.TimeUnitEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter class for time unit conversions.
 * <p>
 * This implementation supports conversion between multiple time units
 * (e.g., minute, day, year, etc.) using {@link TimeUnitEnum}.
 * It operates on {@code Double} values.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 *     TimeConverter converter = new TimeConverter();
 *     double result = converter.convert("minute", "day", 10000.0);
 * }</pre>
 *
 * @see TimeUnitEnum
 * @see IUnitConverter
 */
public class TimeConverter implements IUnitConverter<Double>{

    /**
     * Default constructor for TimeConverter.
     */
    public TimeConverter() {
        // Default constructor
    }

    /**
     * Converts a value from one time unit to another, using unit names as strings.
     *
     * @param fromUnit the source unit name (e.g., "minute")
     * @param toUnit   the target unit name (e.g., "day")
     * @param value    the time value to convert
     * @return the converted value in the target unit
     * @throws IllegalArgumentException if the unit names are invalid
     */
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        TimeUnitEnum from = TimeUnitEnum.fromString(fromUnit);
        TimeUnitEnum to = TimeUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a value from one time unit to another using {@link TimeUnitEnum} values.
     *
     * @param from  the source {@code TimeUnitEnum}
     * @param to    the target {@code TimeUnitEnum}
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    public Double convert(TimeUnitEnum from, TimeUnitEnum to, Double value){
        double inSeconds = value * from.toSeconds();
        return inSeconds / to.toSeconds();
    }

    /**
     * Returns a list of names to display in CLI for all supported time units.
     *
     * @return a list of unit names (e.g., "second", "minute")
     */
    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(TimeUnitEnum.values())
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());
    }

    /**
     * Returns the Java class representing the value type handled by this converter.
     *
     * @return {@code Double.class}
     */
    @Override
    public Class<Double> getValueType() {
        return Double.class;
    }
}
