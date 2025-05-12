package unit_converter;

import unit_converter.enums.NumberSystemEnum;

/**
 * Converter for number systems.
 * This class implements the IUnitConverter interface to provide conversion functionality
 * between different number systems (e.g., binary, decimal, hexadecimal, etc.).
 */
public class NumberSystemConverter implements IUnitConverter<String> {
    
    /**
     * Default constructor for NumberSystemConverter.
     */
    public NumberSystemConverter() {
        // Default constructor
    }
    
    @Override
    public String convert(String fromUnit, String toUnit, String value) {
        NumberSystemEnum from = NumberSystemEnum.fromString(fromUnit);
        NumberSystemEnum to = NumberSystemEnum.fromString(toUnit);
        return convert(from, to, value);
    }
    
    /**
     * Converts a value from one number system to another.
     * The conversion is performed by first converting to decimal (base 10),
     * then to the target number system.
     *
     * @param from the source number system
     * @param to the target number system
     * @param value the value to convert as a string
     * @return the converted value in the target number system
     * @throws IllegalArgumentException if the value is invalid for the source number system
     */
    public String convert(NumberSystemEnum from, NumberSystemEnum to, String value) {
        if (value == null || value.trim().isEmpty()) {
            return "0";
        }
        
        // Clean the input value based on the source base
        String cleanedValue = cleanInput(value, from);
        
        // First convert to decimal (base 10)
        long decimalValue;
        try {
            decimalValue = Long.parseLong(cleanedValue, from.getBase());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format for " + from.name());
        }
        
        // Then convert from decimal to target base
        String result = Long.toString(decimalValue, to.getBase());
        
        // Format the result based on the target base
        switch (to) {
            case BINARY:
                return "0b" + result;
            case OCTAL:
                return "0" + result;
            case HEXADECIMAL:
                return "0x" + result.toUpperCase();
            default:
                return result;
        }
    }
    
    private String cleanInput(String value, NumberSystemEnum base) {
        value = value.trim();
        
        // Remove base-specific prefixes
        switch (base) {
            case BINARY:
                if (value.toUpperCase().startsWith("0B")) {
                    value = value.substring(2);
                }
                break;
            case OCTAL:
                if (value.startsWith("0") && value.length() > 1) {
                    value = value.substring(1);
                }
                break;
            case HEXADECIMAL:
                if (value.toUpperCase().startsWith("0X") || value.startsWith("#")) {
                    value = value.substring(value.startsWith("#") ? 1 : 2);
                }
                break;
            default:
                // No prefix to remove for decimal
                break;
        }
        
        return value;
    }
} 