package unit_converter.enums;

/**
 * Enumeration of force units and their conversion factors to the SI base unit: {@code newton (N)}.
 * <p>
 * Each enum constant represents a unit of force, with a defined multiplier
 * to convert values into newtons.
 * </p>
 *
 * <p>Supported units include:</p>
 * <ul>
 *   <li>Newton (N)</li>
 *   <li>Kilonewton (kN)</li>
 *   <li>Dyne</li>
 *   <li>Kilopond / Kilogram-force</li>
 *   <li>Pound-force, Ounce-force, Poundal</li>
 *   <li>Kip, Ton-force</li>
 * </ul>
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
     * Returns the conversion factor to newtons.
     *
     * @return the equivalent of 1 unit in newtons
     */
    public double toNewton() { return toNewton; }

    /**
     * Parses a string into a {@code ForceUnitEnum} constant.
     * <p>
     * This method is case-insensitive and allows unit names with spaces.
     * For example, {@code "pound force"} and {@code "POUND_FORCE"} are both valid.
     * </p>
     *
     * @param str the string to parse
     * @return the corresponding {@code ForceUnitEnum}
     * @throws IllegalArgumentException if the input is invalid
     */
    public static ForceUnitEnum fromString(String str){
        return ForceUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}