package unit_converter;

import unit_converter.enums.AreaUnitEnum;

public class AreaConverter implements IUnitConverter<Double>{
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        AreaUnitEnum from = AreaUnitEnum.fromString(fromUnit);
        AreaUnitEnum to = AreaUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    public Double convert(AreaUnitEnum from, AreaUnitEnum to, Double value){
        double inSquareMeters = value * from.toSquareMeter();
        return inSquareMeters / to.toSquareMeter();

    }
}
