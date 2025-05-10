package unit_converter;

import unit_converter.enums.ConverterTypeEnum;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConverterFactory {

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

    @SuppressWarnings("unchecked")
    public static <T> IUnitConverter<T> getConverter(String typeName) {
        //return (IUnitConverter<T>) converters.get(type);
        ConverterTypeEnum type = ConverterTypeEnum.fromString(typeName).orElse(null);
        if (type == null) {
            return null;
        }
        return (IUnitConverter<T>) converters.get(type);
    }

    public static List<String> getConverterListNames() {
        return converters.keySet().stream()
                .map(ConverterTypeEnum::getDisplayName)
                .collect(Collectors.toList());
    }
}

