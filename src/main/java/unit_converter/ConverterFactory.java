package unit_converter;

import unit_converter.enums.ConverterTypeEnum;
import unit_converter.enums.EnumDisplayUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Factory class responsible for providing unit converters based on a given type.
 * <p>
 * This class holds a fixed, immutable set of converters mapped to {@link ConverterTypeEnum}.
 * It exposes methods to retrieve a converter by its name and to list all available converter types.
 * </p>
 *
 * <p>This class is non-instantiable and provides only static access.</p>
 *
 * Example usage:
 * <pre>{@code
 *     IUnitConverter<Double> converter = ConverterFactory.getConverter("length");
 *     double result = converter.convert("meter", "kilometer", 1500.0);
 * }</pre>
 *
 * @see IUnitConverter
 * @see ConverterTypeEnum
 */
public class ConverterFactory {

    /**
     * public constructor to prevent instantiation of this utility class.
     *
     * @throws IllegalStateException always thrown when the constructor is called
     */
    public ConverterFactory(){
        throw new IllegalStateException("Illegal state");
    }

    /**
     * Immutable mapping of all available converter types to their concrete implementations.
     * <p>
     * Uses {@code Map.ofEntries()} to ensure immutability and thread safety.
     * </p>
     */
    private static final Map<ConverterTypeEnum, IUnitConverter<?>> converters = Map.ofEntries(
            Map.entry(ConverterTypeEnum.LENGTH, new LengthConverter()),
            Map.entry(ConverterTypeEnum.VOLUME, new VolumeConverter()),
            Map.entry(ConverterTypeEnum.TEMPERATURE, new TemperatureConverter()),
            Map.entry(ConverterTypeEnum.AREA, new AreaConverter()),
            Map.entry(ConverterTypeEnum.DENSITY, new DensityConverter()),
            Map.entry(ConverterTypeEnum.CURRENCY, new CurrencyConverter()),
            Map.entry(ConverterTypeEnum.PRESSURE, new PressureConverter()),
            Map.entry(ConverterTypeEnum.SPEED, new SpeedConverter()),
            Map.entry(ConverterTypeEnum.ENERGY, new EnergyConverter()),
            Map.entry(ConverterTypeEnum.TIME, new TimeConverter()),
            Map.entry(ConverterTypeEnum.FORCE, new ForceConverter()),
            Map.entry(ConverterTypeEnum.BINARY, new NumberSystemConverter())
            //instead of Hashmap, Map.ofEntries make the collection immutable
    );

    /**
     * Returns a converter corresponding to the specified type name.
     * <p>
     * The type name is matched using {@link ConverterTypeEnum#fromString(String)}, which
     * supports case-insensitive and user-friendly names.
     * </p>
     *
     * @param typeName the name of the converter type (e.g., "length", "currency")
     * @param <T>      the value type used by the converter (e.g., {@code Double}, {@code String})
     * @return the matching converter, or {@code null} if not found
     */
    @SuppressWarnings("unchecked")
    public static <T> IUnitConverter<T> getConverter(String typeName) {
        ConverterTypeEnum type = ConverterTypeEnum.fromString(typeName).orElse(null);
        if (type == null) {
            return null;
        }
        return (IUnitConverter<T>) converters.get(type);
    }

    /**
     * Returns a list of all available converter type names as user-friendly display names.
     *
     * @return a list of converter names (e.g., "Length", "Volume", "Currency", etc.)
     */
    public static List<String> getConverterListNames() {
        return Arrays.stream(ConverterTypeEnum.values())
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());
    }
}

