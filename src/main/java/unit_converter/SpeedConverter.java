package unit_converter;

import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.SpeedUnitEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter class for speed unit conversions.
 * <p>
 * This implementation supports conversion between multiple speed units
 * (e.g., Meter per Hour, Kilometer per Hour, Inch per minute, etc.) using {@link SpeedUnitEnum}.
 * It operates on {@code Double} values.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 *     SpeedConverter converter = new SpeedConverter();
 *     double result = converter.convert("Meter per Hour", "Kilometer per Hour", 10000.0);
 * }</pre>
 *
 * @see SpeedUnitEnum
 * @see IUnitConverter
 */
public class SpeedConverter implements IUnitConverter<Double>{

    /**
     * Converts a value from one speed unit to another, using unit names as strings.
     *
     * @param fromUnit the source unit name (e.g., "Meter per Hour")
     * @param toUnit   the target unit name (e.g., "Kilometer per Hour")
     * @param value    the speed value to convert
     * @return the converted value in the target unit
     * @throws IllegalArgumentException if the unit names are invalid
     */
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        SpeedUnitEnum from = SpeedUnitEnum.fromString(fromUnit);
        SpeedUnitEnum to = SpeedUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a value from one area unit to another using {@link SpeedUnitEnum} values.
     *
     * @param from  the source {@code SpeedUnitEnum}
     * @param to    the target {@code SpeedUnitEnum}
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    public Double convert(SpeedUnitEnum from, SpeedUnitEnum to, Double value){
        double meterPerSeconds = value * from.toMeterPerSecond();
        return meterPerSeconds/ to.toMeterPerSecond();
    }

    /**
     * Returns a list of names to display in CLI for all supported speed units.
     *
     * @return a list of unit names (e.g., "Meter per Hour", "Kilometer per Hour")
     */
    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(SpeedUnitEnum.values())
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
