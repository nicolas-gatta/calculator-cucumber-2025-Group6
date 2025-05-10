package unit_converter;

import unit_converter.enums.AreaUnitEnum;
import unit_converter.enums.CurrencyUnitEnum;
import unit_converter.enums.EnergyUnitEnum;
import unit_converter.enums.EnumDisplayUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnergyConverter implements IUnitConverter<Double>{
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        EnergyUnitEnum from = EnergyUnitEnum.fromString(fromUnit);
        EnergyUnitEnum to = EnergyUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    public Double convert(EnergyUnitEnum from, EnergyUnitEnum to, Double value){
        double inJoule = value * from.toJoule();
        return inJoule / to.toJoule();
    }

    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(EnergyUnitEnum.values())
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());
    }

    @Override
    public Class<Double> getValueType() {
        return Double.class;
    }
}
