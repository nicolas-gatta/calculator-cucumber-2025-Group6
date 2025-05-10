package unit_converter;

import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.SpeedUnitEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SpeedConverter implements IUnitConverter<Double>{
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        SpeedUnitEnum from = SpeedUnitEnum.fromString(fromUnit);
        SpeedUnitEnum to = SpeedUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    public Double convert(SpeedUnitEnum from, SpeedUnitEnum to, Double value){
        double meterPerSeconds = value * from.toMeterPerSecond();
        return meterPerSeconds/ to.toMeterPerSecond();
    }

    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(SpeedUnitEnum.values())
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());
    }

    @Override
    public Class<Double> getValueType() {
        return Double.class;
    }


}
