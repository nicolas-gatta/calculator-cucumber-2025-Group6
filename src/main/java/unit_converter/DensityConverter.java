package unit_converter;

import unit_converter.enums.DensityUnitEnum;

/**
 * Converter for density units.
 * Implements the IUnitConverter interface for handling density conversions.
 */
public class DensityConverter implements IUnitConverter<Double>{
    
    /**
     * Default constructor for DensityConverter.
     */
    public DensityConverter() {
        // Default constructor
    }
    
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        DensityUnitEnum from = DensityUnitEnum.fromString(fromUnit);
        DensityUnitEnum to = DensityUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a value from one density unit to another.
     * 
     * @param from the source density unit
     * @param to the target density unit
     * @param value the density value to convert
     * @return the converted density value in the target unit
     */
    public Double convert(DensityUnitEnum from, DensityUnitEnum to, double value){
        double kgPerCubicMeters = value * from.toKgPerCubicMeter();
        return kgPerCubicMeters/to.toKgPerCubicMeter();
    }
}
