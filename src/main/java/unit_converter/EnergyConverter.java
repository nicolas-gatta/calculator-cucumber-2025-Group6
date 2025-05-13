package unit_converter;

import unit_converter.enums.EnergyUnitEnum;
import unit_converter.enums.EnumDisplayUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter class for Energy unit conversions.
 * <p>
 * This implementation supports conversion between multiple Energy units
 * (e.g., Joule, Watt hour, Calorie, etc.) using {@link EnergyUnitEnum}.
 * It operates on {@code Double} values.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 *     EnergyConverter converter = new EnergyConverter();
 *     double result = converter.convert("joule", "calorie", 10000.0);
 * }</pre>
 *
 * @see EnergyUnitEnum
 * @see IUnitConverter
 */
public class EnergyConverter implements IUnitConverter<Double>{



    /**
     * Default constructor for EnergyConverter.
     */
    public EnergyConverter() {
        // Default constructor
    }

    /**
     * Converts a value from one Energy unit to another, using unit names as strings.
     *
     * @param fromUnit the source unit name (e.g., "joule")
     * @param toUnit   the target unit name (e.g., "calorie")
     * @param value    the energy value to convert
     * @return the converted value in the target unit
     * @throws IllegalArgumentException if the unit names are invalid
     */
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        EnergyUnitEnum from = EnergyUnitEnum.fromString(fromUnit);
        EnergyUnitEnum to = EnergyUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a value from one area unit to another using {@link EnergyUnitEnum} values.
     *
     * @param from  the source {@code EnergyUnitEnum}
     * @param to    the target {@code EnergyUnitEnum}
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    public Double convert(EnergyUnitEnum from, EnergyUnitEnum to, Double value){
        double inJoule = value * from.toJoule();
        return inJoule / to.toJoule();
    }

    /**
     * Returns a list of names to display in CLI for all supported Energy units.
     *
     * @return a list of unit names (e.g., "Joule", "Calorie")
     */
    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(EnergyUnitEnum.values())
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
