package unit_converter.enums;

/**
 * Enumeration of supported areas for area conversion.
 * <p>
 * Each area constant stores its relative value to 1 Square meter, allowing
 * bidirectional conversion between the defined areas.
 * </p>
 *
 * Example: 1.0 Square meter = 0.0001 Square centimeter (as defined in this enum)
 */
public enum AreaUnitEnum {
    /**
     * Square millimeter unit (0.000001 square meters).
     */
    SQUARE_MILLIMETER(0.000001),

    /**
     * Square meter unit (base unit with factor 1.0).
     */
    SQUARE_METER(1.0),

    /**
     * Square centimeter unit (0.0001 square meters).
     */
    SQUARE_CENTIMETER(0.0001),

    /**
     * Square kilometer unit (1,000,000 square meters).
     */
    SQUARE_KILOMETER(1000000.0),

    /**
     * Square inch unit (0.00064516 square meters).
     */
    SQUARE_INCH(0.00064516),

    /**
     * Square foot unit (0.09290304 square meters).
     */
    SQUARE_FOOT(0.09290304),

    /**
     * Square yard unit (0.83612736 square meters).
     */
    SQUARE_YARD(0.83612736),

    /**
     * Square mile unit (2,589,988.110336 square meters).
     */
    SQUARE_MILE(2589988.110336),

    /**
     * Acre unit (4,046.8564224 square meters).
     */
    ACRE(4046.8564224),

    /**
     * Hectare unit (10,000 square meters).
     */
    HECTARE(10000.0);

    private final double toSquareMeter;

    /**
     * Constructs an area unit with the specified conversion factor to square meters.
     *
     * @param toSquareMeter the conversion factor to square meters
     */
    AreaUnitEnum(double toSquareMeter) {
        this.toSquareMeter = toSquareMeter;
    }

    /**
     * Returns the conversion factor from this unit to square meters.
     *
     * @return the number of square meters equivalent to one unit of this area measurement
     */
    public double toSquareMeter() {
        return toSquareMeter;
    }

    /**
     * Returns the enum value that matches the input string (case-insensitive).
     *
     * @param str the area name (e.g., "square meter", "square kilometer")
     * @return the matching {@code AreaUnitEnum}
     * @throws IllegalArgumentException if the string doesn't match any constant
     */
    public static AreaUnitEnum fromString(String str) {
        return AreaUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}
