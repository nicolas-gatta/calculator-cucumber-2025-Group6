package unit_converter.enums;

public enum AreaUnitEnum {
    SQUARE_MILLIMETER(0.000001),
    SQUARE_METER(1.0),
    SQUARE_CENTIMETER(0.0001),
    SQUARE_KILOMETER(1000000.0),
    SQUARE_INCH(0.00064516),
    SQUARE_FOOT(0.09290304),
    SQUARE_YARD(0.83612736),
    SQUARE_MILE(2589988.110336),
    ACRE(4046.8564224),
    HECTARE(10000.0);

    private final double toSquareMeter;

    AreaUnitEnum(double toSquareMeter) {
        this.toSquareMeter = toSquareMeter;
    }

    public double toSquareMeter() {
        return toSquareMeter;
    }

    public static AreaUnitEnum fromString(String str) {
        return AreaUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}
