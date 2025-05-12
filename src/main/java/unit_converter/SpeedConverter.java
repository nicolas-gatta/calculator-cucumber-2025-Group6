package unit_converter;

import unit_converter.enums.SpeedUnitEnum;

/**
 * Converter for speed units.
 * Provides functionality to convert between different speed units.
 */
public class SpeedConverter implements IUnitConverter<Double>{
    
    /**
     * Default constructor for SpeedConverter.
     */
    public SpeedConverter() {
        // Default constructor
    }
    
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        SpeedUnitEnum from = SpeedUnitEnum.fromString(fromUnit);
        SpeedUnitEnum to = SpeedUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a speed value from one unit to another.
     *
     * @param from The source speed unit
     * @param to The target speed unit
     * @param value The value to convert
     * @return The converted value
     */
    public Double convert(SpeedUnitEnum from, SpeedUnitEnum to, Double value){
        double meterPerSeconds = value * from.toMeterPerSecond();
        return meterPerSeconds/ to.toMeterPerSecond();
    }
}
