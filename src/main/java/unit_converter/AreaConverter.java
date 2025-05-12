package unit_converter;

import unit_converter.enums.AreaUnitEnum;

/**
 * This class implements the IUnitConverter interface.
 * It has a method to convert a value from one area unit to another.
 */
public class AreaConverter implements IUnitConverter<Double>{
    
    /**
     * Constructs a new AreaConverter.
     */
    public AreaConverter() {
        // Default constructor
    }
    
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        AreaUnitEnum from = AreaUnitEnum.fromString(fromUnit);
        AreaUnitEnum to = AreaUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a value from one area unit to another.
     *
     * @param from the source area unit
     * @param to the target area unit
     * @param value the value to convert
     * @return the converted value
     */
    public Double convert(AreaUnitEnum from, AreaUnitEnum to, Double value){
        double inSquareMeters = value * from.toSquareMeter();
        return inSquareMeters / to.toSquareMeter();
    }
}
