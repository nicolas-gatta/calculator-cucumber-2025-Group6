package unit_converter;

import unit_converter.enums.AreaUnitEnum;
import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.ForceUnitEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Converter class for Force unit conversions.
 * <p>
 * This implementation supports conversion between multiple force units
 * (e.g., newton, kip, kilo force, etc.) using {@link ForceUnitEnum}.
 * It operates on {@code Double} values.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 *     ForceConverter converter = new ForceConverter();
 *     double result = converter.convert("newton", "kip", 10000.0);
 * }</pre>
 *
 * @see ForceUnitEnum
 * @see IUnitConverter
 */
public class ForceConverter implements IUnitConverter<Double>{

    /**
     * Converts a value from one force unit to another, using unit names as strings.
     *
     * @param fromUnit the source unit name (e.g., "newton")
     * @param toUnit   the target unit name (e.g., "kip")
     * @param value    the force value to convert
     * @return the converted value in the target unit
     * @throws IllegalArgumentException if the unit names are invalid
     */
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        ForceUnitEnum from = ForceUnitEnum.fromString(fromUnit);
        ForceUnitEnum to = ForceUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a value from one force unit to another using {@link ForceUnitEnum} values.
     *
     * @param from  the source {@code ForceUnitEnum}
     * @param to    the target {@code ForceUnitEnum}
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    public Double convert(ForceUnitEnum from, ForceUnitEnum to, Double value){
        double inNewton = value * from.toNewton();
        return inNewton / to.toNewton();
    }

    /**
     * Returns a list of names to display in CLI for all supported force units.
     *
     * @return a list of unit names (e.g., "newton", "kip")
     */
    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(ForceUnitEnum.values())
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
