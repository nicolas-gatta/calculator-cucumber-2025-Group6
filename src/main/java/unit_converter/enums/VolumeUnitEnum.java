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
    MILLILITER(0.001),
    CENTILITER(0.01),
    DECILITER(0.1),
    LITER(1.0),
    CUBIC_METER(1000.0),
    TEASPOON(0.00492892),
    TABLESPOON(0.0147868),
    FLUID_OUNCE(0.0295735),
    CUP(0.24),
    PINT(0.473176),
    QUART(0.946353),
    GALLON(3.78541),
    CUBIC_INCH(0.0163871),
    CUBIC_FOOT(28.3168);

    private final double toLiter;

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
