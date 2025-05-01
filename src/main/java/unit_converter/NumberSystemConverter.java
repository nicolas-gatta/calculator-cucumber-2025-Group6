package unit_converter;

import unit_converter.enums.NumberSystemEnum;

public class NumberSystemConverter implements IUnitConverter<String> {
    
    @Override
    public String convert(String fromUnit, String toUnit, String value) {
        NumberSystemEnum from = NumberSystemEnum.fromString(fromUnit);
        NumberSystemEnum to = NumberSystemEnum.fromString(toUnit);
        return convert(from, to, value);
    }
    
    public String convert(NumberSystemEnum from, NumberSystemEnum to, String value) {
        // Remove any prefixes (0x, 0b, etc.) and spaces
        value = value.trim().toUpperCase()
                .replace("0X", "").replace("0B", "")
                .replace("#", "").replace("0", "");
        
        // If value is empty after cleaning, return "0"
        if (value.isEmpty()) {
            return "0";
        }
        
        // First convert to decimal (base 10)
        long decimalValue;
        try {
            decimalValue = Long.parseLong(value, from.getBase());
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
} 