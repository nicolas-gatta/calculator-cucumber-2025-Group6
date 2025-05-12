package unit_converter.enums;

/**
 * Enumeration of number systems with their respective bases.
 * This enum provides constants for common number systems used in computing
 * and methods to convert between them.
 */
public enum NumberSystemEnum {
    /** Decimal: Base-10 number system (0-9) */
    DECIMAL(10),
    /** Binary: Base-2 number system (0-1) */
    BINARY(2),
    /** Octal: Base-8 number system (0-7) */
    OCTAL(8),
    /** Hexadecimal: Base-16 number system (0-9, A-F) */
    HEXADECIMAL(16);

    /** The base of this number system */
    private final int base;

    /**
     * Constructor for NumberSystemEnum.
     * 
     * @param base the base of the number system
     */
    NumberSystemEnum(int base) {
        this.base = base;
    }

    /**
     * Gets the base of this number system.
     * 
     * @return the base value (e.g., 2 for binary, 10 for decimal)
     */
    public int getBase() {
        return base;
    }

    /**
     * Converts a string representation of a number system to its enum constant.
     * The string is trimmed and converted to uppercase.
     * 
     * @param str the string representation of the number system
     * @return the corresponding NumberSystemEnum constant
     * @throws IllegalArgumentException if the string does not match any enum constant
     */
    public static NumberSystemEnum fromString(String str) {
        return NumberSystemEnum.valueOf(str.trim().toUpperCase());
    }
} 