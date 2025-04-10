package unit_converter;

import unit_converter.enums.ForceUnitEnum;

public class ForceConverter implements IUnitConverter<Double>{
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        ForceUnitEnum from = ForceUnitEnum.fromString(fromUnit);
        ForceUnitEnum to = ForceUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    public Double convert(ForceUnitEnum from, ForceUnitEnum to, Double value){
        double inNewton = value * from.toNewton();
        return inNewton / to.toNewton();
    }
}
