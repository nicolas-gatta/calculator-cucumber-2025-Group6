package unit_converter.enums;

/**
 * Enumeration of force units with their conversion factors to Newtons.
 * This enum provides constants for various force units and methods to convert between them.
 */
public enum ForceUnitEnum {
    /** Dyne: CGS unit of force (1 dyne = 10^-5 N) */
    DYNE(0.00001),
    /** Kilogram-force: force exerted by Earth's gravity on one kilogram of mass */
    KILO_FORCE(9.80665),
    /** Kilopond: equivalent to kilogram-force */
    KILOPOND(9.80665),
    /** Kip: 1000 pound-force */
    KIP(4448.222),
    /** Newton: SI unit of force */
    NEWTON(1.0),
    /** Kilonewton: 1000 newtons */
    KILONEWTON(1000.0),
    /** Ounce-force: 1/16 of a pound-force */
    OUNCE_FORCE(0.2780139),
    /** Poundal: force needed to accelerate 1 pound mass at 1 ft/s² */
    POUNDAL(0.138255),
    /** Pound-force: force exerted by Earth's gravity on one pound of mass */
    POUND_FORCE(4.448222),
    /** Ton-force: 2000 pound-force */
    TON_FORCE(8896.443);

    /** Conversion factor to convert from this unit to Newtons */
    private final double toNewton;

    /**
     * Constructor for ForceUnitEnum.
     * 
     * @param toNewton the conversion factor to convert from this unit to Newtons
     */
    ForceUnitEnum(double toNewton){
        this.toNewton = toNewton;
    }

    /**
     * Gets the conversion factor to convert from this unit to Newtons.
     * 
     * @return the conversion factor to Newtons
     */
    public double toNewton() { return toNewton; }

    /**
     * Converts a string representation of a force unit to its enum constant.
     * The string is trimmed, converted to uppercase, and spaces are replaced with underscores.
     * 
     * @param str the string representation of the force unit
     * @return the corresponding ForceUnitEnum constant
     * @throws IllegalArgumentException if the string does not match any enum constant
     */
    public static ForceUnitEnum fromString(String str){
        return ForceUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}