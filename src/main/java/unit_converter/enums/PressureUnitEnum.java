package unit_converter.enums;

public enum PressureUnitEnum {
    PASCAL(1.0),
    HECTOPASCAL(100.0),
    KILOPASCAL(1000.0),
    BAR(100000.0),
    ATMOSPHERE(101325.0),
    TORR(133.322),
    PSI(6894.76);

    private final double toPascal;

    PressureUnitEnum(double toPascal) {
        this.toPascal = toPascal;
    }

    public double toPascal() {
        return toPascal;
    }

    public static PressureUnitEnum fromString(String str) {
        return PressureUnitEnum.valueOf(str.trim().toUpperCase());
    }
}
