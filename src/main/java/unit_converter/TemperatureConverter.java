package unit_converter;

import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.TemperatureUnitEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter class for temperature unit conversions.
 * <p>
 * This implementation supports conversion between multiple temperature units
 * (e.g., Celcius, Fahrenheit, Kelvin, etc.) using {@link TemperatureUnitEnum}.
 * It operates on {@code Double} values.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 *     TemperatureConverter converter = new TemperatureConverter();
 *     double result = converter.convert("square_meter", "hectare", 10000.0);
 * }</pre>
 *
 * @see TemperatureUnitEnum
 * @see IUnitConverter
 */
public class TemperatureConverter implements IUnitConverter<Double>{

    /**
     * Default constructor for TemperatureConverter.
     */
    public TemperatureConverter() {
        // Default constructor
    }

    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        TemperatureUnitEnum from = TemperatureUnitEnum.fromString(fromUnit);
        TemperatureUnitEnum to = TemperatureUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a value from one temperature unit to another using {@link TemperatureUnitEnum} values.
     *
     * @param from  the source {@code TemperatureUnitEnum}
     * @param to    the target {@code TemperatureUnitEnum}
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    public Double convert(TemperatureUnitEnum from, TemperatureUnitEnum to, Double value){

        if(from == to){
            return value;
        }

        // convert value temporary to celsius
        double tempCelsius = switch (from) {
            case CELSIUS -> value;
            case FAHRENHEIT -> (value - 32) * 5 / 9;
            case KELVIN -> value - 273.15;
        };

        // convert celsius to other unit
        return switch (to) {
            case CELSIUS -> tempCelsius;
            case FAHRENHEIT -> tempCelsius * 9 / 5 + 32;
            case KELVIN -> tempCelsius + 273.15;
        };
    }

    /**
     * Returns a list of names to display in CLI for all supported temperature units.
     *
     * @return a list of unit names (e.g., "celcius", "kelvin")
     */
    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(TemperatureUnitEnum.values())
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
