package unit_converter;

import unit_converter.enums.DensityUnitEnum;
import unit_converter.enums.EnumDisplayUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DensityConverter implements IUnitConverter<Double>{
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        DensityUnitEnum from = DensityUnitEnum.fromString(fromUnit);
        DensityUnitEnum to = DensityUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    public Double convert(DensityUnitEnum from, DensityUnitEnum to, double value){
        double kgPerCubicMeters = value * from.toKgPerCubicMeter();
        return kgPerCubicMeters/to.toKgPerCubicMeter();
    }

    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(DensityUnitEnum.values())
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());
    }

    @Override
    public Class<Double> getValueType() {
        return Double.class;
    }
}
