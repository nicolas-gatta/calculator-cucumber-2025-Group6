package unit_converter;

import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.VolumeUnitEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VolumeConverter implements IUnitConverter<Double>{
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        VolumeUnitEnum from = VolumeUnitEnum.fromString(fromUnit);
        VolumeUnitEnum to = VolumeUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    public Double convert(VolumeUnitEnum from, VolumeUnitEnum to, Double value){
        double inLiter = value * from.toLiter();
        return inLiter / to.toLiter();
    }

    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(VolumeUnitEnum.values())
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());
    }

    @Override
    public Class<Double> getValueType() {
        return Double.class;
    }
}
