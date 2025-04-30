package unit_converter;

import unit_converter.enums.PressureUnitEnum;

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
}
