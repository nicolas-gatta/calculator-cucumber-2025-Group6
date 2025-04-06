package unit_converter;

import unit_converter.enums.TimeUnitEnum;

public class TimeConverter implements IUnitConverter<Double>{

    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        TimeUnitEnum from = TimeUnitEnum.fromString(fromUnit);
        TimeUnitEnum to = TimeUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    public Double convert(TimeUnitEnum from, TimeUnitEnum to, Double value){
        double inSeconds = value * from.toSeconds();
        return inSeconds / to.toSeconds();
    }
}
