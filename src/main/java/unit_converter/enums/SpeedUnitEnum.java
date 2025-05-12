package unit_converter.enums;

/**
 * Enumeration of speed units with conversion factors to meters per second.
 * Provides functionality for converting between different speed units.
 */
public enum SpeedUnitEnum {
    /** Represents centimeters per minute (0.0001667 m/s) */
    CENTIMETER_PER_MINUTE(0.0001667),
    /** Represents centimeters per second (0.01 m/s) */
    CENTIMETER_PER_SECOND(0.01),
    /** Represents feet per hour (0.0000846667 m/s) */
    FOOT_PER_HOUR(0.0000846667),
    /** Represents feet per minute (0.00508 m/s) */
    FOOT_PER_MINUTE(0.00508),
    /** Represents feet per second (0.3048 m/s) */
    FOOT_PER_SECOND(0.3048),
    /** Represents inches per minute (0.000423333 m/s) */
    INCH_PER_MINUTE(0.000423333),
    /** Represents inches per second (0.0254 m/s) */
    INCH_PER_SECOND(0.0254),
    /** Represents kilometers per hour (0.2777778 m/s) */
    KILOMETER_PER_HOUR(0.2777778),
    /** Represents kilometers per second (1000 m/s) */
    KILOMETER_PER_SECOND(1000),
    /** Represents meters per hour (0.0002777778 m/s) */
    METER_PER_HOUR(0.0002777778),
    /** Represents meters per minute (0.01667 m/s) */
    METER_PER_MINUTE(0.01667),
    /** Represents meters per second (base unit) */
    METER_PER_SECOND(1.0);

    private final double toMeterPerSecond;

    SpeedUnitEnum(double toMeterPerSecond){
        this.toMeterPerSecond = toMeterPerSecond;
    }

    /**
     * Returns the conversion factor from this speed unit to meters per second.
     * 
     * @return The conversion factor to meters per second
     */
    public double toMeterPerSecond(){
        return toMeterPerSecond;
    }

    /**
     * Converts a string representation to the corresponding SpeedUnitEnum.
     * The string is trimmed, converted to uppercase, and spaces are replaced with underscores.
     * 
     * @param str The string to convert
     * @return The corresponding SpeedUnitEnum
     * @throws IllegalArgumentException if the string does not match any enum constant
     */
    public static SpeedUnitEnum fromString(String str) {
        return SpeedUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}
