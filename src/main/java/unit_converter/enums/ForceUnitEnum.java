package unit_converter.enums;

public enum ForceUnitEnum {
    DYNE(0.00001),
    KILO_FORCE(9.80665),
    KILOPOND(9.80665),
    KIP(4448.222),
    NEWTON(1.0),
    KILONEWTON(1000.0),
    OUNCE_FORCE(0.2780139),
    POUNDAL(0.138255),
    POUND_FORCE(4.448222),
    TON_FORCE(8896.443);

    private final double toNewton;

    ForceUnitEnum(double toNewton){
        this.toNewton = toNewton;
    }

    public double toNewton() { return toNewton; }

    public static ForceUnitEnum fromString(String str){
        return ForceUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}