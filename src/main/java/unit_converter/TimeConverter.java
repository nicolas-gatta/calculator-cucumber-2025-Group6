package unit_converter;

import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.TimeUnitEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TimeConverter implements IUnitConverter<Double>{

    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        TimeUnitEnum from = TimeUnitEnum.fromString(fromUnit);
        TimeUnitEnum to = TimeUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    public Double convert(TimeUnitEnum from, TimeUnitEnum to, Double value){
        double inSeconds = value * from.toSeconds();
        return inSeconds / to.toSeconds();
    }

    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(TimeUnitEnum.values())
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());
    }

    @Override
    public Class<Double> getValueType() {
        return Double.class;
    }
}
