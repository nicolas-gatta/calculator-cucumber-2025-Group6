package unit_converter.enums;

public enum DensityUnitEnum{
    GRAM_PER_CUBIC_CENTIMETER(1000.0),
    KILOGRAM_PER_CUBIC_CENTIMETER(1000000.0),
    GRAM_PER_CUBIC_METER(0.001),
    KILOGRAM_PER_CUBIC_METER(1.0),
    GRAM_PER_MILLIMETER(1000.0),
    GRAM_PER_LITER(1.0),
    POUND_PER_CUBIC_FOOT(16.0185),
    POUND_PER_CUBIC_INCH(27679.9),
    OUNCE_PER_GALLON_US(7.4891517);

    private final double toKgPerCubicMeter;

    DensityUnitEnum(double toKgPerCubicMeter) {
        this.toKgPerCubicMeter = toKgPerCubicMeter;
    }

    public double toKgPerCubicMeter() {
        return toKgPerCubicMeter;
    }

    public static DensityUnitEnum fromString(String str) {
        return DensityUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}
