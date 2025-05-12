package unit_converter;

import unit_converter.enums.AreaUnitEnum;
import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.LengthUnitEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Converter class for length unit conversions.
 * <p>
 * This implementation supports conversion between multiple length units
 * (e.g., Meter, Kilometer, Inch, etc.) using {@link LengthUnitEnum}.
 * It operates on {@code Double} values.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 *     LengthConverter converter = new LengthConverter();
 *     double result = converter.convert("meter", "kilometer", 10000.0);
 * }</pre>
 *
 * @see LengthUnitEnum
 * @see IUnitConverter
 */
public class LengthConverter implements IUnitConverter<Double>{

    /**
     * Default constructor for LengthConverter.
     */
    public LengthConverter() {
        // Default constructor
    }

    /**
     * Converts a length value from one unit to another using string unit identifiers.
     * This method parses the string unit identifiers to LengthUnitEnum objects and delegates
     * to the typed convert method.
     *
     * @param fromUnit the source length unit as a string
     * @param toUnit the target length unit as a string
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        LengthUnitEnum from = LengthUnitEnum.fromString(fromUnit);
        LengthUnitEnum to = LengthUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a value from one length unit to another using {@link LengthUnitEnum} values.
     *
     * @param from  the source {@code LengthUnitEnum}
     * @param to    the target {@code LengthUnitEnum}
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    public Double convert(LengthUnitEnum from, LengthUnitEnum to, Double value){
        double inMeter = value * from.toMeter();
        return inMeter / to.toMeter();
    }

    /**
     * Returns a list of names to display in CLI for all supported area units.
     *
     * @return a list of unit names (e.g., "meter", "kilometer")
     */
    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(LengthUnitEnum.values())
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
