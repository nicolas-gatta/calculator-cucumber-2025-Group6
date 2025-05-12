package unit_converter.enums;

/**
 * Enum representing different density units with their conversion factors to kg/m³.
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
     * Returns the conversion factor from this density unit to kg/m³.
     * 
     * @return the conversion factor to kg/m³
     */
    public double toKgPerCubicMeter() {
        return toKgPerCubicMeter;
    }

    /**
     * Converts a string representation to the corresponding DensityUnitEnum value.
     * 
     * @param str the string to convert
     * @return the matching DensityUnitEnum
     */
    public static DensityUnitEnum fromString(String str) {
        return DensityUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}
