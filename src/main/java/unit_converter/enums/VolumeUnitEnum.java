package unit_converter.enums;

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

    public double toLiter() {
        return toLiter;
    }

    public static VolumeUnitEnum fromString(String str) {
        return VolumeUnitEnum.valueOf(str.trim().toUpperCase());
    }
}
