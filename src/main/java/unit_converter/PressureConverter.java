package unit_converter;

import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.PressureUnitEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter class for pressure unit conversions.
 * <p>
 * This implementation supports conversion between multiple pressure units
 * (e.g., Pascal, Bar, Torr, etc.) using {@link PressureUnitEnum}.
 * It operates on {@code Double} values.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 *     PressureConverter converter = new PressureConverter();
 *     double result = converter.convert("Pascal", "Bar", 10000.0);
 * }</pre>
 *
 * @see PressureUnitEnum
 * @see IUnitConverter
 */
public class PressureConverter implements IUnitConverter<Double>{

    /**
     * Default constructor for PressureConverter.
     */
    public PressureConverter() {
        // Default constructor
    }

    /**
     * Converts a pressure value from one unit to another using string unit identifiers.
     * This method parses the string unit identifiers to PressureUnitEnum objects and delegates
     * to the typed convert method.
     *
     * @param fromUnit the source pressure unit as a string
     * @param toUnit the target pressure unit as a string
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        PressureUnitEnum from = PressureUnitEnum.fromString(fromUnit);
        PressureUnitEnum to = PressureUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a value from one pressure unit to another using {@link PressureUnitEnum} values.
     *
     * @param from  the source {@code PressureUnitEnum}
     * @param to    the target {@code PressureUnitEnum}
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    public Double convert(PressureUnitEnum from, PressureUnitEnum to, Double value){
        double inPascal = value * from.toPascal();
        return inPascal / to.toPascal();
    }

    /**
     * Returns a list of names to display in CLI for all supported pressure units.
     *
     * @return a list of unit names (e.g., "Pascal", "Bar")
     */
    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(PressureUnitEnum.values())
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
