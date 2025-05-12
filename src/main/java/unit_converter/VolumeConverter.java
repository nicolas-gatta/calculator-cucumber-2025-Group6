package unit_converter;

import unit_converter.enums.AreaUnitEnum;
import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.VolumeUnitEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter class for volume unit conversions.
 * <p>
 * This implementation supports conversion between multiple volume units
 * (e.g., liter, teaspoon, gallon, etc.) using {@link VolumeUnitEnum}.
 * It operates on {@code Double} values.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 *     VolumeConverter converter = new VolumeConverter();
 *     double result = converter.convert("liter", "gallon", 10000.0);
 * }</pre>
 *
 * @see VolumeUnitEnum
 * @see IUnitConverter
 */
public class VolumeConverter implements IUnitConverter<Double>{

    /**
     * Converts a value from one volume unit to another, using unit names as strings.
     *
     * @param fromUnit the source unit name (e.g., "liter")
     * @param toUnit   the target unit name (e.g., "gallon")
     * @param value    the volume value to convert
     * @return the converted value in the target unit
     * @throws IllegalArgumentException if the unit names are invalid
     */
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        VolumeUnitEnum from = VolumeUnitEnum.fromString(fromUnit);
        VolumeUnitEnum to = VolumeUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a value from one volume unit to another using {@link VolumeUnitEnum} values.
     *
     * @param from  the source {@code VolumeUnitEnum}
     * @param to    the target {@code VolumeUnitEnum}
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    public Double convert(VolumeUnitEnum from, VolumeUnitEnum to, Double value){
        double inLiter = value * from.toLiter();
        return inLiter / to.toLiter();
    }

    /**
     * Returns a list of names to display in CLI for all supported volume units.
     *
     * @return a list of unit names (e.g., "liter", "gallon")
     */
    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(VolumeUnitEnum.values())
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
