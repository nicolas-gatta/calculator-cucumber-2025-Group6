package unit_converter;

import unit_converter.enums.VolumeUnitEnum;

/**
 * Converter for volume units.
 * Provides functionality to convert between different volume units.
 */
public class VolumeConverter implements IUnitConverter<Double>{
    
    /**
     * Default constructor for VolumeConverter.
     */
    public VolumeConverter() {
        // Default constructor
    }
    
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        VolumeUnitEnum from = VolumeUnitEnum.fromString(fromUnit);
        VolumeUnitEnum to = VolumeUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a volume value from one unit to another.
     *
     * @param from The source volume unit
     * @param to The target volume unit
     * @param value The value to convert
     * @return The converted value
     */
    public Double convert(VolumeUnitEnum from, VolumeUnitEnum to, Double value){
        double inLiter = value * from.toLiter();
        return inLiter / to.toLiter();
    }
}
