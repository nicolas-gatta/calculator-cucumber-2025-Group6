package unit_converter;

import unit_converter.enums.ConverterTypeEnum;
import java.util.Map;
import java.util.Optional;

/**
 * Factory class for creating unit converters.
 * <p>
 * This class provides a centralized way to access different types of unit converters
 * (length, volume, temperature, etc.) through a static method. It maintains a map
 * of converter types to their corresponding converter instances.
 * </p>
 */
public class ConverterFactory {

    /**
     * Default constructor.
     * <p>
     * This constructor is not meant to be used as this is a utility class with only static methods.
     * </p>
     */
    private ConverterFactory() {
        // Private constructor to prevent instantiation
    }

    //instead of Hashmap, Map.ofEntries make the collection immutable
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
            Map.entry(ConverterTypeEnum.FORCE, new ForceConverter())
    );

    /**
     * Gets a unit converter for the specified type.
     * <p>
     * This method returns a unit converter that can convert between units of the specified type.
     * For example, if "LENGTH" is provided, a converter that can convert between meters, feet, etc.
     * will be returned.
     * </p>
     *
     * @param <T> the type parameter for the converter (typically Double for numerical conversions)
     * @param typeName the name of the converter type (e.g., "LENGTH", "VOLUME", "TEMPERATURE")
     * @return the appropriate unit converter, or null if the type is not supported
     */
    @SuppressWarnings("unchecked")
    public static <T> IUnitConverter<T> getConverter(String typeName) {
        //return (IUnitConverter<T>) converters.get(type);
        ConverterTypeEnum type = ConverterTypeEnum.fromString(typeName).orElse(null);
        if (type == null) {
            return null;
        }
        return (IUnitConverter<T>) converters.get(type);
    }
}

