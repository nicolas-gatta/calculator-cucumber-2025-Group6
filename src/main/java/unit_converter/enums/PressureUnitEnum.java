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
    /** Pascal: SI unit of pressure (N/m²) */
    PASCAL(1.0),
    /** Hectopascal: 100 pascals, commonly used in meteorology */
    HECTOPASCAL(100.0),
    /** Kilopascal: 1000 pascals */
    KILOPASCAL(1000.0),
    /** Bar: 100,000 pascals, approximately equal to atmospheric pressure at sea level */
    BAR(100000.0),
    /** Standard atmosphere: defined as 101,325 pascals */
    ATMOSPHERE(101325.0),
    /** Torr: approximately 1/760 of standard atmospheric pressure */
    TORR(133.322),
    /** Pounds per square inch: imperial unit of pressure */
    PSI(6894.76);

    /** Conversion factor to convert from this unit to Pascals */
    private final double toPascal;

    /**
     * Constructor for PressureUnitEnum.
     *
     * @param toPascal the conversion factor to convert from this unit to Pascals
     */
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
