package unit_converter;

import unit_converter.enums.EnergyUnitEnum;

/**
 * Converter for energy units.
 * Implements the IUnitConverter interface for handling energy conversions.
 */
public class EnergyConverter implements IUnitConverter<Double>{
    
    /**
     * Default constructor for EnergyConverter.
     */
    public EnergyConverter() {
        // Default constructor
    }
    
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        EnergyUnitEnum from = EnergyUnitEnum.fromString(fromUnit);
        EnergyUnitEnum to = EnergyUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a value from one energy unit to another.
     * 
     * @param from the source energy unit
     * @param to the target energy unit
     * @param value the energy value to convert
     * @return the converted energy value in the target unit
     */
    public Double convert(EnergyUnitEnum from, EnergyUnitEnum to, Double value){
        double inJoule = value * from.toJoule();
        return inJoule / to.toJoule();
    }
}
