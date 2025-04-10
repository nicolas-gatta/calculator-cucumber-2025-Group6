package unit_converter;

import unit_converter.enums.VolumeUnitEnum;

public class VolumeConverter implements IUnitConverter<Double>{
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        VolumeUnitEnum from = VolumeUnitEnum.fromString(fromUnit);
        VolumeUnitEnum to = VolumeUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    public Double convert(VolumeUnitEnum from, VolumeUnitEnum to, Double value){
        double inLiter = value * from.toLiter();
        return inLiter / to.toLiter();
    }
}
