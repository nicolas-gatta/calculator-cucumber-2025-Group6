package unit_converter;

import unit_converter.enums.DensityUnitEnum;
import unit_converter.enums.EnumDisplayUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter class for Density unit conversions.
 * <p>
 * This implementation supports conversion between multiple Density units
 * (e.g., gram per liter, kilogram per cubic meter, pound per cubic foot, etc.) using {@link DensityUnitEnum}.
 * It operates on {@code Double} values.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 *     DensityConverter converter = new DensityConverter();
 *     double result = converter.convert("gram per liter", "kilogram per cubic meter", 10000.0);
 * }</pre>
 *
 * @see DensityUnitEnum
 * @see IUnitConverter
 */
public class DensityConverter implements IUnitConverter<Double>{

    /**
     * Default constructor for DensityConverter.
     */
    public DensityConverter() {
        // Default constructor
    }


    /**
     * Converts a value from one density unit to another, using unit names as strings.
     *
     * @param fromUnit the source unit name (e.g., "gram per liter")
     * @param toUnit   the target unit name (e.g., "kilogram per cubic meter")
     * @param value    the density value to convert
     * @return the converted value in the target unit
     * @throws IllegalArgumentException if the unit names are invalid
     */
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        DensityUnitEnum from = DensityUnitEnum.fromString(fromUnit);
        DensityUnitEnum to = DensityUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a value from one area unit to another using {@link DensityUnitEnum} values.
     *
     * @param from  the source {@code DensityUnitEnum}
     * @param to    the target {@code DensityUnitEnum}
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    public Double convert(DensityUnitEnum from, DensityUnitEnum to, double value){
        double kgPerCubicMeters = value * from.toKgPerCubicMeter();
        return kgPerCubicMeters/to.toKgPerCubicMeter();
    }

    /**
     * Returns a list of names to display in CLI for all supported area units.
     *
     * @return a list of unit names (e.g., "gram per liter", "kilogram per cubic meter")
     */
    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(DensityUnitEnum.values())
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
