package unit_converter.enums;

/**
 * Enumeration representing supported number systems (bases).
 * <p>
 * This enum defines numeric bases used for conversion between different
 * number representations (e.g., binary, decimal, hexadecimal).
 * </p>
 *
 * Each constant stores its numeric base (e.g., 2 for binary, 16 for hexadecimal).
 *
 * @see unit_converter.NumberSystemConverter
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
     * Returns the base value (e.g., 2, 8, 10, 16) for this number system.
     *
     * @return the numeric base
     */
    public int getBase() {
        return base;
    }

    /**
     * Returns the {@code NumberSystemEnum} matching the given string.
     * <p>
     * Matching is case-insensitive and ignores leading/trailing whitespace.
     * </p>
     *
     * @param str the input name (e.g., "binary", "DECIMAL")
     * @return the matching enum constant
     * @throws IllegalArgumentException if no match is found
     */
    public static NumberSystemEnum fromString(String str) {
        return NumberSystemEnum.valueOf(str.trim().toUpperCase());
    }
} 