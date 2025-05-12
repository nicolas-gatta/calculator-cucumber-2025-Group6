package unit_converter.enums;

/**
 * Enumeration of length units with their conversion factors to meters.
 * This enum provides constants for various length units and methods to convert between them.
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
     * Gets the conversion factor to convert from this unit to meters.
     * 
     * @return the conversion factor to meters
     */
    public double toMeter() {
        return toMeter;
    }

    /**
     * Converts a string representation of a length unit to its enum constant.
     * The string is trimmed, converted to uppercase, and spaces are replaced with underscores.
     * 
     * @param str the string representation of the length unit
     * @return the corresponding LengthUnitEnum constant
     * @throws IllegalArgumentException if the string does not match any enum constant
     */
    public static LengthUnitEnum fromString(String str) {
        return LengthUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}
