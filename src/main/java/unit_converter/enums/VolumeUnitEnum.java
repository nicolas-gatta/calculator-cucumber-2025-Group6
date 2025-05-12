package unit_converter.enums;

/**
 * Enumeration of volume units and their conversion factors to the base unit: {@code liter (L)}.
 * <p>
 * This enum supports conversion between metric and imperial volume units using predefined
 * conversion factors to liters.
 * </p>
 *
 * <p>Supported units include:</p>
 * <ul>
 *   <li>Milliliter, Centiliter, Deciliter, Liter, Cubic Meter</li>
 *   <li>Teaspoon, Tablespoon, Fluid Ounce, Cup, Pint, Quart, Gallon</li>
 *   <li>Cubic Inch, Cubic Foot</li>
 * </ul>
 */
public enum VolumeUnitEnum {
    /** Milliliter: 1/1000 of a liter */
    MILLILITER(0.001),
    /** Centiliter: 1/100 of a liter */
    CENTILITER(0.01),
    /** Deciliter: 1/10 of a liter */
    DECILITER(0.1),
    /** Liter: base unit of volume in the metric system */
    LITER(1.0),
    /** Cubic meter: 1000 liters */
    CUBIC_METER(1000.0),
    /** Teaspoon: approximately 4.93 milliliters */
    TEASPOON(0.00492892),
    /** Tablespoon: approximately 14.79 milliliters */
    TABLESPOON(0.0147868),
    /** Fluid ounce: approximately 29.57 milliliters */
    FLUID_OUNCE(0.0295735),
    /** Cup: approximately 240 milliliters */
    CUP(0.24),
    /** Pint: approximately 473 milliliters */
    PINT(0.473176),
    /** Quart: approximately 946 milliliters */
    QUART(0.946353),
    /** Gallon: approximately 3.79 liters */
    GALLON(3.78541),
    /** Cubic inch: approximately 16.39 milliliters */
    CUBIC_INCH(0.0163871),
    /** Cubic foot: approximately 28.32 liters */
    CUBIC_FOOT(28.3168);

    /** Conversion factor to convert from this unit to liters */
    private final double toLiter;

    /**
     * Constructor for VolumeUnitEnum.
     *
     * @param toLiter the conversion factor to convert from this unit to liters
     */
    VolumeUnitEnum(double toLiter) {
        this.toLiter = toLiter;
    }

    /**
     * Returns the conversion factor to liters.
     *
     * @return the equivalent of one unit in liters
     */
    public double toLiter() {
        return toLiter;
    }

    /**
     * Parses a string into a {@code VolumeUnitEnum}.
     * <p>
     * Matching is case-insensitive and ignores surrounding whitespace.
     * </p>
     *
     * @param str the string representing a unit (e.g., "liter", "CUP")
     * @return the corresponding {@code VolumeUnitEnum}
     * @throws IllegalArgumentException if the input is invalid
     */
    public static VolumeUnitEnum fromString(String str) {
        return VolumeUnitEnum.valueOf(str.trim().toUpperCase());
    }
}
