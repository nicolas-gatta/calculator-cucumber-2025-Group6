package unit_converter;

import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.LengthUnitEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LengthConverter implements IUnitConverter<Double>{
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        LengthUnitEnum from = LengthUnitEnum.fromString(fromUnit);
        LengthUnitEnum to = LengthUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    public Double convert(LengthUnitEnum from, LengthUnitEnum to, Double value){
        double inMeter = value * from.toMeter();
        return inMeter / to.toMeter();
    }

    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(LengthUnitEnum.values())
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());
    }

    @Override
    public Class<Double> getValueType() {
        return Double.class;
    }


}
