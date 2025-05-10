package unit_converter;

import unit_converter.enums.AreaUnitEnum;
import unit_converter.enums.EnumDisplayUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AreaConverter implements IUnitConverter<Double>{
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        AreaUnitEnum from = AreaUnitEnum.fromString(fromUnit);
        AreaUnitEnum to = AreaUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    public Double convert(AreaUnitEnum from, AreaUnitEnum to, Double value){
        double inSquareMeters = value * from.toSquareMeter();
        return inSquareMeters / to.toSquareMeter();
    }

    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(AreaUnitEnum.values())
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());
    }

    @Override
    public Class<Double> getValueType() {
        return Double.class;
    }
}
