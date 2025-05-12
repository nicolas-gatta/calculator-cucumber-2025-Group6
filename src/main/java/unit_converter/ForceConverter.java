package unit_converter;

import unit_converter.enums.ForceUnitEnum;

/**
 * Converter for force units.
 * This class implements the IUnitConverter interface to provide conversion functionality
 * between different force units (e.g., Newton, Pound-force, etc.).
 */
public class ForceConverter implements IUnitConverter<Double>{
    
    /**
     * Default constructor for ForceConverter.
     */
    public ForceConverter() {
        // Default constructor
    }
    
    /**
     * Converts a force value from one unit to another using string unit identifiers.
     * This method parses the string unit identifiers to ForceUnitEnum objects and delegates
     * to the typed convert method.
     *
     * @param fromUnit the source force unit as a string
     * @param toUnit the target force unit as a string
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        ForceUnitEnum from = ForceUnitEnum.fromString(fromUnit);
        ForceUnitEnum to = ForceUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a force value from one unit to another using ForceUnitEnum types.
     * The conversion is performed by converting the value to Newtons first, then
     * to the target unit.
     *
     * @param from the source force unit
     * @param to the target force unit
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    public Double convert(ForceUnitEnum from, ForceUnitEnum to, Double value){
        double inNewton = value * from.toNewton();
        return inNewton / to.toNewton();
    }
}
