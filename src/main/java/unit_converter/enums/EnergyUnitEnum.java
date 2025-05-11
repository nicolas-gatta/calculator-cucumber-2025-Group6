package unit_converter.enums;

/**
 * Enumeration of energy units with conversion factors to joules.
 * <p>
 * This enum allows conversion between various energy measurements such as
 * electron volts, calories, and kilowatt-hours by referencing the standard
 * SI unit: the joule.
 * </p>
 */
public enum EnergyUnitEnum {
    ELECTRON_VOLT(1.60218E-19),
    JOULE(1.0),
    KILOJOULE(1000.0),
    MEGAJOULE(1000000.0),
    CALORIE(4.184),
    KILOCALORIE(4184.0),
    WATT_HOUR(3600.0),
    KILOWATT_HOUR(3600000.0),
    BTU(1055.06),
    ERG(1e-7);

    private final double toJoule;

    EnergyUnitEnum(double toJoule) {
        this.toJoule = toJoule;
    }

    /**
     * Returns the conversion factor to joules.
     *
     * @return the number of joules equivalent to one unit of this energy type
     */
    public double toJoule() {
        return toJoule;
    }

    /**
     * Returns the matching enum constant from a string.
     * Spaces in names are allowed and automatically converted to underscores.
     *
     * @param str the unit name (e.g., "kiloJoule", "watt hour")
     * @return the corresponding {@code EnergyUnitEnum}
     * @throws IllegalArgumentException if the string doesn't match
     */
    public static EnergyUnitEnum fromString(String str) {
        return EnergyUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}
