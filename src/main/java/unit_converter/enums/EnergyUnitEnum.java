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
    /**
     * Electron volt (eV) with conversion factor to joules.
     */
    ELECTRON_VOLT(1.60218E-19),

    /**
     * Joule (J) - base unit with conversion factor 1.0.
     */
    JOULE(1.0),

    /**
     * Kilojoule (kJ) with conversion factor to joules.
     */
    KILOJOULE(1000.0),

    /**
     * Megajoule (MJ) with conversion factor to joules.
     */
    MEGAJOULE(1000000.0),

    /**
     * Calorie (cal) with conversion factor to joules.
     */
    CALORIE(4.184),

    /**
     * Kilocalorie (kcal) with conversion factor to joules.
     */
    KILOCALORIE(4184.0),

    /**
     * Watt-hour (Wh) with conversion factor to joules.
     */
    WATT_HOUR(3600.0),

    /**
     * Kilowatt-hour (kWh) with conversion factor to joules.
     */
    KILOWATT_HOUR(3600000.0),

    /**
     * British Thermal Unit (BTU) with conversion factor to joules.
     */
    BTU(1055.06),

    /**
     * Erg with conversion factor to joules.
     */
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
