package unit_converter.enums;

/**
 * Enumeration of pressure units with their conversion factors to Pascals.
 * This enum provides constants for various pressure units and methods to convert between them.
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
     * Gets the conversion factor to convert from this unit to Pascals.
     * 
     * @return the conversion factor to Pascals
     */
    public double toPascal() {
        return toPascal;
    }

    /**
     * Converts a string representation of a pressure unit to its enum constant.
     * The string is trimmed and converted to uppercase.
     * 
     * @param str the string representation of the pressure unit
     * @return the corresponding PressureUnitEnum constant
     * @throws IllegalArgumentException if the string does not match any enum constant
     */
    public static PressureUnitEnum fromString(String str) {
        return PressureUnitEnum.valueOf(str.trim().toUpperCase());
    }
}
