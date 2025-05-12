package unit_converter;

import unit_converter.enums.AreaUnitEnum;
import unit_converter.enums.EnumDisplayUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Converter class for area unit conversions.
 * <p>
 * This implementation supports conversion between multiple area units
 * (e.g., square meters, acres, hectares, etc.) using {@link AreaUnitEnum}.
 * It operates on {@code Double} values.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 *     AreaConverter converter = new AreaConverter();
 *     double result = converter.convert("square_meter", "hectare", 10000.0);
 * }</pre>
 *
 * @see AreaUnitEnum
 * @see IUnitConverter
 */
public class AreaConverter implements IUnitConverter<Double>{

    /**
     * Converts a value from one area unit to another, using unit names as strings.
     *
     * @param fromUnit the source unit name (e.g., "square_meter")
     * @param toUnit   the target unit name (e.g., "hectare")
     * @param value    the area value to convert
     * @return the converted value in the target unit
     * @throws IllegalArgumentException if the unit names are invalid
     */
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        AreaUnitEnum from = AreaUnitEnum.fromString(fromUnit);
        AreaUnitEnum to = AreaUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a value from one area unit to another using {@link AreaUnitEnum} values.
     *
     * @param from  the source {@code AreaUnitEnum}
     * @param to    the target {@code AreaUnitEnum}
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    public Double convert(AreaUnitEnum from, AreaUnitEnum to, Double value){
        double inSquareMeters = value * from.toSquareMeter();
        return inSquareMeters / to.toSquareMeter();
    }

    /**
     * Returns a list of names to display in CLI for all supported area units.
     *
     * @return a list of unit names (e.g., "Square meter", "Hectare")
     */
    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(AreaUnitEnum.values())
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
