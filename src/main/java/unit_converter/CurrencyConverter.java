package unit_converter;

import unit_converter.enums.CurrencyUnitEnum;
import unit_converter.enums.EnumDisplayUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter class for currency unit conversions.
 * <p>
 * This implementation supports conversion between multiple currency units
 * (e.g., EUR, USD, GBP, etc.) using {@link CurrencyUnitEnum}.
 * It operates on {@code Double} values.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 *     CurrencyConverter converter = new CurrencyConverter();
 *     double result = converter.convert("Eur", "Usd", 100.0);
 * }</pre>
 *
 * @see CurrencyUnitEnum
 * @see IUnitConverter
 */
public class CurrencyConverter implements IUnitConverter<Double>{

    /**
     * Converts a value from one currency unit to another, using unit names as strings.
     *
     * @param fromUnit the source unit name (e.g., "Eur")
     * @param toUnit   the target unit name (e.g., "Usd")
     * @param value    the currency value to convert
     * @return the converted value in the target unit
     * @throws IllegalArgumentException if the unit names are invalid
     */
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        CurrencyUnitEnum from = CurrencyUnitEnum.fromString(fromUnit);
        CurrencyUnitEnum to = CurrencyUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a value from one area unit to another using {@link CurrencyUnitEnum} values.
     *
     * @param from  the source {@code CurrencyUnitEnum}
     * @param to    the target {@code CurrencyUnitEnum}
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    public Double convert(CurrencyUnitEnum from, CurrencyUnitEnum to, Double value){
        double inEuro = value / from.toEuro();
        return inEuro * to.toEuro();
    }

    /**
     * Returns a list of names to display in CLI for all supported area units.
     *
     * @return a list of unit names (e.g., "Eur", "Usd")
     */
    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(CurrencyUnitEnum.values())
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
