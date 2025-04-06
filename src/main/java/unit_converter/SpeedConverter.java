package unit_converter;

import unit_converter.enums.SpeedUnitEnum;

public class SpeedConverter implements IUnitConverter<Double>{
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        SpeedUnitEnum from = SpeedUnitEnum.fromString(fromUnit);
        SpeedUnitEnum to = SpeedUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    public Double convert(SpeedUnitEnum from, SpeedUnitEnum to, Double value){
        double meterPerSeconds = value * from.toMeterPerSecond();
        return meterPerSeconds/ to.toMeterPerSecond();
    }
}
