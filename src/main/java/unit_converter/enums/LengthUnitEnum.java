package unit_converter.enums;

/**
 * Enumeration of length units with conversion factors to the SI base unit: {@code meter (m)}.
 * <p>
 * Each constant represents a length unit and its corresponding multiplier to convert
 * to meters. This enum is used for unit conversions in length-related computations.
 * </p>
 *
 * <p>Supported units include both metric and imperial systems, as well as scientific units:</p>
 * <ul>
 *   <li>Millimeter, Centimeter, Decimeter, Meter, Kilometer</li>
 *   <li>Inch, Foot, Yard, Mile, Nautical Mile</li>
 *   <li>Light Year, Angstrom</li>
 * </ul>
 */
public enum LengthUnitEnum{
    /** Millimeter: 1/1000 of a meter */
    MILLIMETER(0.001),
    /** Centimeter: 1/100 of a meter */
    CENTIMETER(0.01),
    /** Decimeter: 1/10 of a meter */
    DECIMETER(0.1),
    /** Meter: SI base unit of length */
    METER(1.0),
    /** Kilometer: 1000 meters */
    KILOMETER(1000.0),
    /** Inch: 0.0254 meters (exactly) */
    INCH(0.0254),
    /** Foot: 12 inches or 0.3048 meters (exactly) */
    FOOT(0.3048),
    /** Yard: 3 feet or 0.9144 meters (exactly) */
    YARD(0.9144),
    /** Mile: 1609.344 meters (exactly) */
    MILE(1609.344),
    /** Nautical mile: 1852 meters (exactly) */
    NAUTICAL_MILE(1852.0),
    /** Light year: distance light travels in a vacuum in one Julian year */
    LIGHT_YEAR(9460730472580800.0),
    /** Angstrom: 10^-10 meters, used for atomic scales */
    ANGSTROM(1E-10);

    /** Conversion factor to convert from this unit to meters */
    private final double toMeter;

    /**
     * Constructor for LengthUnitEnum.
     *
     * @param toMeter the conversion factor to convert from this unit to meters
     */
    LengthUnitEnum(double toMeter) {
        this.toMeter = toMeter;
    }

    /**
     * Returns the conversion factor to meters.
     *
     * @return the equivalent of one unit in meters
     */
    public double toMeter() {
        return toMeter;
    }

    /**
     * Parses a string into a {@code LengthUnitEnum}.
     * <p>
     * Matching is case-insensitive and supports both underscore and space-based input.
     * For example, {@code "nautical mile"} and {@code "NAUTICAL_MILE"} are both valid.
     * </p>
     *
     * @param str the string representing a unit
     * @return the matching {@code LengthUnitEnum}
     * @throws IllegalArgumentException if the input is invalid or not found
     */
    public static LengthUnitEnum fromString(String str) {
        return LengthUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}
