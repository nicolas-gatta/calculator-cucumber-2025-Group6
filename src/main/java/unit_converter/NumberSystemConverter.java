package unit_converter;

import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.NumberSystemEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter for numeric values between different number systems (bases).
 * <p>
 * This implementation supports conversions between binary, octal, decimal, and hexadecimal,
 * using {@link NumberSystemEnum} as the unit representation. Values are handled as {@code String}
 * and formatted accordingly depending on the base.
 * </p>
 *
 * <p>Supported bases include:</p>
 * <ul>
 *     <li>Binary (base 2)</li>
 *     <li>Octal (base 8)</li>
 *     <li>Decimal (base 10)</li>
 *     <li>Hexadecimal (base 16)</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 *     NumberSystemConverter converter = new NumberSystemConverter();
 *     String result = converter.convert("binary", "hexadecimal", "0b1011"); // returns "0xB"
 * }</pre>
 *
 * @see NumberSystemEnum
 * @see IUnitConverter
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
     * Converts a value between number systems using enum representations.
     *
     * @param from  the source number system
     * @param to    the target number system
     * @param value the value to convert
     * @return the formatted result string in the target base
     * @throws IllegalArgumentException if the input format is invalid for the source base
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

    /**
     * Removes formatting prefixes (like {@code 0x}, {@code 0b}, or {@code #}) from input strings,
     * based on the detected number system.
     *
     * @param value the raw input string
     * @param base  the number system base
     * @return the cleaned string without prefix
     */
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

    /**
     * Returns a list of supported number systems in a user-friendly format.
     *
     * @return a list of display names (e.g., "Binary", "Hexadecimal")
     */
    @Override
    public List<String> getConverterUnitListNames() {
        return Arrays.stream(NumberSystemEnum.values())
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());
    }

    /**
     * Returns the class type used by this converter for input/output values.
     *
     * @return {@code String.class}
     */
    @Override
    public Class<String> getValueType() {
        return String.class;
    }


}