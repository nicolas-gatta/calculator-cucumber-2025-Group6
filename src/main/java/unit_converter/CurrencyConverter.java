package unit_converter;

import unit_converter.enums.CurrencyUnitEnum;

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
}
