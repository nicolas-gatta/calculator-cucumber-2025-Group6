package unit_converter;

import unit_converter.enums.TimeUnitEnum;

/**
 * Converter for time units.
 * Provides functionality to convert between different time units.
 */
public class TimeConverter implements IUnitConverter<Double>{
    
    /**
     * Default constructor for TimeConverter.
     */
    public TimeConverter() {
        // Default constructor
    }

    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        TimeUnitEnum from = TimeUnitEnum.fromString(fromUnit);
        TimeUnitEnum to = TimeUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a time value from one unit to another.
     *
     * @param from The source time unit
     * @param to The target time unit
     * @param value The value to convert
     * @return The converted value
     */
    public Double convert(TimeUnitEnum from, TimeUnitEnum to, Double value){
        double inSeconds = value * from.toSeconds();
        return inSeconds / to.toSeconds();
    }
}
