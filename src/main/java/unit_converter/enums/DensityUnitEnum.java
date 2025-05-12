package unit_converter.enums;

/**
 * Enumeration representing units of density and their conversion factors
 * to the SI base unit: {@code kilogram per cubic meter (kg/m³)}.
 * <p>
 * Each enum constant defines a specific density unit along with its
 * multiplier to convert values into kilograms per cubic meter.
 * </p>
 *
 * <p>Examples of supported units:</p>
 * <ul>
 *   <li>Gram per cubic centimeter (g/cm³)</li>
 *   <li>Kilogram per cubic meter (kg/m³)</li>
 *   <li>Pound per cubic foot (lb/ft³)</li>
 *   <li>Ounce per gallon (US)</li>
 * </ul>
 */
public enum DensityUnitEnum{
    /**
     * Gram per cubic centimeter (g/cm³) with conversion factor to kg/m³.
     */
    GRAM_PER_CUBIC_CENTIMETER(1000.0),

    /**
     * Kilogram per cubic centimeter (kg/cm³) with conversion factor to kg/m³.
     */
    KILOGRAM_PER_CUBIC_CENTIMETER(1000000.0),

    /**
     * Gram per cubic meter (g/m³) with conversion factor to kg/m³.
     */
    GRAM_PER_CUBIC_METER(0.001),

    /**
     * Kilogram per cubic meter (kg/m³) - base unit with conversion factor 1.0.
     */
    KILOGRAM_PER_CUBIC_METER(1.0),

    /**
     * Gram per millimeter (g/mm³) with conversion factor to kg/m³.
     */
    GRAM_PER_MILLIMETER(1000.0),

    /**
     * Gram per liter (g/L) with conversion factor to kg/m³.
     */
    GRAM_PER_LITER(1.0),

    /**
     * Pound per cubic foot (lb/ft³) with conversion factor to kg/m³.
     */
    POUND_PER_CUBIC_FOOT(16.0185),

    /**
     * Pound per cubic inch (lb/in³) with conversion factor to kg/m³.
     */
    POUND_PER_CUBIC_INCH(27679.9),

    /**
     * Ounce per US gallon (oz/gal) with conversion factor to kg/m³.
     */
    OUNCE_PER_GALLON_US(7.4891517);

    private final double toKgPerCubicMeter;

    DensityUnitEnum(double toKgPerCubicMeter) {
        this.toKgPerCubicMeter = toKgPerCubicMeter;
    }

    /**
     * Returns the conversion factor to kilograms per cubic meter.
     *
     * @return the equivalent of 1 unit in kg/m³
     */
    public double toKgPerCubicMeter() {
        return toKgPerCubicMeter;
    }

    /**
     * Parses a string into a {@code DensityUnitEnum} constant.
     * <p>
     * Matching is case-insensitive and supports names with spaces
     * (e.g., {@code "gram per liter"} or {@code "GRAM_PER_LITER"}).
     * </p>
     *
     * @param str the string to parse
     * @return the corresponding {@code DensityUnitEnum}
     * @throws IllegalArgumentException if the input is invalid
     */
    public static DensityUnitEnum fromString(String str) {
        return DensityUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}
