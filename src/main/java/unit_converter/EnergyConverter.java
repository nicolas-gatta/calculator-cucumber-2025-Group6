package unit_converter;

import unit_converter.enums.EnergyUnitEnum;

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
}
