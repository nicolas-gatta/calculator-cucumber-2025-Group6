package unit_converter;

import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.ForceUnitEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ForceConverter implements IUnitConverter<Double>{
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        ForceUnitEnum from = ForceUnitEnum.fromString(fromUnit);
        ForceUnitEnum to = ForceUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    public Double convert(ForceUnitEnum from, ForceUnitEnum to, Double value){
        double inNewton = value * from.toNewton();
        return inNewton / to.toNewton();
    }

    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(ForceUnitEnum.values())
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());
    }

    @Override
    public Class<Double> getValueType() {
        return Double.class;
    }
}
