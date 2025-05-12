package unit_converter.enums;

/**
 * Enumeration of pressure units and their conversion factors to the SI base unit: {@code pascal (Pa)}.
 * <p>
 * Each constant represents a unit of pressure and stores a multiplier to convert it to pascals.
 * This enum is used by {@code PressureConverter} to support conversions between common pressure units.
 * </p>
 *
 * <p>Supported units include:</p>
 * <ul>
 *   <li>Pascal (Pa)</li>
 *   <li>Hectopascal (hPa)</li>
 *   <li>Kilopascal (kPa)</li>
 *   <li>Bar</li>
 *   <li>Atmosphere (atm)</li>
 *   <li>Torr (mmHg)</li>
 *   <li>Pounds per square inch (psi)</li>
 * </ul>
 */
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

    /**
     * Returns the conversion factor to pascals.
     *
     * @return the equivalent of 1 unit in pascals
     */
    public double toPascal() {
        return toPascal;
    }

    /**
     * Parses a string into a {@code PressureUnitEnum}.
     * <p>
     * Matching is case-insensitive and allows trimming whitespace.
     * </p>
     *
     * @param str the string to parse (e.g., "bar", "PSI")
     * @return the matching {@code PressureUnitEnum}
     * @throws IllegalArgumentException if no matching unit is found
     */
    public static PressureUnitEnum fromString(String str) {
        return PressureUnitEnum.valueOf(str.trim().toUpperCase());
    }
}
