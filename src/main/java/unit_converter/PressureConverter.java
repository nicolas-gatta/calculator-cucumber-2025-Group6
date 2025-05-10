package unit_converter;

import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.PressureUnitEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PressureConverter implements IUnitConverter<Double>{
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        PressureUnitEnum from = PressureUnitEnum.fromString(fromUnit);
        PressureUnitEnum to = PressureUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    public Double convert(PressureUnitEnum from, PressureUnitEnum to, Double value){
        double inPascal = value * from.toPascal();
        return inPascal / to.toPascal();
    }

    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(PressureUnitEnum.values())
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());
    }

    @Override
    public Class<Double> getValueType() {
        return Double.class;
    }
}
