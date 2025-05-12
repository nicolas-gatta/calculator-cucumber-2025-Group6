package unit_converter;

import unit_converter.enums.PressureUnitEnum;

/**
 * Converter for pressure units.
 * This class implements the IUnitConverter interface to provide conversion functionality
 * between different pressure units (e.g., Pascal, Bar, PSI, etc.).
 */
public class PressureConverter implements IUnitConverter<Double>{
    
    /**
     * Default constructor for PressureConverter.
     */
    public PressureConverter() {
        // Default constructor
    }
    
    /**
     * Converts a pressure value from one unit to another using string unit identifiers.
     * This method parses the string unit identifiers to PressureUnitEnum objects and delegates
     * to the typed convert method.
     *
     * @param fromUnit the source pressure unit as a string
     * @param toUnit the target pressure unit as a string
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        PressureUnitEnum from = PressureUnitEnum.fromString(fromUnit);
        PressureUnitEnum to = PressureUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a pressure value from one unit to another using PressureUnitEnum types.
     * The conversion is performed by converting the value to Pascals first, then
     * to the target unit.
     *
     * @param from the source pressure unit
     * @param to the target pressure unit
     * @param value the value to convert
     * @return the converted value in the target unit
     */
    public Double convert(PressureUnitEnum from, PressureUnitEnum to, Double value){
        double inPascal = value * from.toPascal();
        return inPascal / to.toPascal();
    }
}
