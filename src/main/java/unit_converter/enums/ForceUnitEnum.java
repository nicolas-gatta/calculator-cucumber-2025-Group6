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