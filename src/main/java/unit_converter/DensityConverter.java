package unit_converter;

import unit_converter.enums.DensityUnitEnum;

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
}
