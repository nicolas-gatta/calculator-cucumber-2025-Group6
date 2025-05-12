package unit_converter;

import unit_converter.enums.LengthUnitEnum;

/**
 * Converter for length units.
 * This class implements the IUnitConverter interface to provide conversion functionality
 * between different length units (e.g., meters, feet, inches, etc.).
 */
public class LengthConverter implements IUnitConverter<Double>{
    
    /**
     * Default constructor for LengthConverter.
     */
    public LengthConverter() {
        // Default constructor
    }
    
    /**
     * Converts a length value from one unit to another using string unit identifiers.
     * This method parses the string unit identifiers to LengthUnitEnum objects and delegates
     * to the typed convert method.
     *
     * @param fromUnit the source length unit as a string
     * @param toUnit the target length unit as a string
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        LengthUnitEnum from = LengthUnitEnum.fromString(fromUnit);
        LengthUnitEnum to = LengthUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a length value from one unit to another using LengthUnitEnum types.
     * The conversion is performed by converting the value to meters first, then
     * to the target unit.
     *
     * @param from the source length unit
     * @param to the target length unit
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    public Double convert(LengthUnitEnum from, LengthUnitEnum to, Double value){
        double inMeter = value * from.toMeter();
        return inMeter / to.toMeter();
    }
}
