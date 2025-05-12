package unit_converter;

import unit_converter.enums.TemperatureUnitEnum;

/**
 * Converter for temperature units.
 * Provides functionality to convert between different temperature scales (Celsius, Fahrenheit, and Kelvin).
 */
public class TemperatureConverter implements IUnitConverter<Double>{
    
    /**
     * Default constructor for TemperatureConverter.
     */
    public TemperatureConverter() {
        // Default constructor
    }
    
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        TemperatureUnitEnum from = TemperatureUnitEnum.fromSting(fromUnit);
        TemperatureUnitEnum to = TemperatureUnitEnum.fromSting(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a temperature value from one unit to another.
     * The conversion is done by first converting to Celsius as an intermediate step,
     * then converting from Celsius to the target unit.
     *
     * @param from The source temperature unit
     * @param to The target temperature unit
     * @param value The value to convert
     * @return The converted value
     */
    public Double convert(TemperatureUnitEnum from, TemperatureUnitEnum to, Double value){

        if(from == to){
            return value;
        }

        // convert value temporary to celsius
        double tempCelsius = switch (from) {
            case CELSIUS -> value;
            case FAHRENHEIT -> (value - 32) * 5 / 9;
            case KELVIN -> value - 273.15;
            //default -> throw new IllegalArgumentException("Invalid temperature unit.");
        };

        // convert celsius to other unit
        return switch (to) {
            case CELSIUS -> tempCelsius;
            case FAHRENHEIT -> tempCelsius * 9 / 5 + 32;
            case KELVIN -> tempCelsius + 273.15;
            //default -> throw new IllegalArgumentException("Invalid temperature unit.");
        };
    }
}
