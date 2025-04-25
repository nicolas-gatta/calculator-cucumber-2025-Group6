package unit_converter.enums;

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

    public double toMeter() {
        return toMeter;
    }

    public static LengthUnitEnum fromString(String str) {
        return LengthUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}
