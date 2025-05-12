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
    SQUARE_MILLIMETER(0.000001),
    SQUARE_METER(1.0),
    SQUARE_CENTIMETER(0.0001),
    SQUARE_KILOMETER(1000000.0),
    SQUARE_INCH(0.00064516),
    SQUARE_FOOT(0.09290304),
    SQUARE_YARD(0.83612736),
    SQUARE_MILE(2589988.110336),
    ACRE(4046.8564224),
    HECTARE(10000.0);

    private final double toSquareMeter;

    AreaUnitEnum(double toSquareMeter) {
        this.toSquareMeter = toSquareMeter;
    }

    /**
     * Returns the conversion factor to Square meter.
     *
     * @return the equivalent of 1 unit of this area in Square meter
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
