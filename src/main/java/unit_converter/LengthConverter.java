package unit_converter;

import unit_converter.enums.LengthUnitEnum;

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
}
