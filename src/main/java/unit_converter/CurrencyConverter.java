package unit_converter;

import unit_converter.enums.CurrencyUnitEnum;
import unit_converter.enums.EnumDisplayUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyConverter implements IUnitConverter<Double>{
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        CurrencyUnitEnum from = CurrencyUnitEnum.fromString(fromUnit);
        CurrencyUnitEnum to = CurrencyUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    public Double convert(CurrencyUnitEnum from, CurrencyUnitEnum to, Double value){
        double inEuro = value / from.toEuro();
        return inEuro * to.toEuro();
    }

    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(CurrencyUnitEnum.values())
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());
    }

    @Override
    public Class<Double> getValueType() {
        return Double.class;
    }
}
