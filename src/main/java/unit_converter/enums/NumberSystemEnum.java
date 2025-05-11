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
    DECIMAL(10),
    BINARY(2),
    OCTAL(8),
    HEXADECIMAL(16);

    private final int base;

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