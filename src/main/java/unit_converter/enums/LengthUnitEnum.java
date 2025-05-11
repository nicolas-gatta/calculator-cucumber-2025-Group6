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
    MILLIMETER(0.001),
    CENTIMETER(0.01),
    DECIMETER(0.1),
    METER(1.0),
    KILOMETER(1000.0),
    INCH(0.0254),
    FOOT(0.3048),
    YARD(0.9144),
    MILE(1609.344),
    NAUTICAL_MILE(1852.0),
    LIGHT_YEAR(9460730472580800.0),
    ANGSTROM(1E-10);

    private final double toMeter;

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
