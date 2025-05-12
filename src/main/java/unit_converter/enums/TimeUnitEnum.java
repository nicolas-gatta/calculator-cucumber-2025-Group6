package unit_converter.enums;

/**
 * Enumeration of time units with conversion factors to seconds.
 * Provides functionality for converting between different time units.
 */
public enum TimeUnitEnum {
    /** Represents a nanosecond (10^-9 seconds) */
    NANOSECOND(0.000000001),
    /** Represents a millisecond (10^-3 seconds) */
    MILLISECOND(0.001),
    /** Represents a second (base unit) */
    SECOND(1.0),
    /** Represents a minute (60 seconds) */
    MINUTE(60.0),
    /** Represents an hour (3600 seconds) */
    HOUR(3600.0),
    /** Represents a day (86400 seconds) */
    DAY(86400.0),
    /** Represents a week (604800 seconds) */
    WEEK(604800.0),
    /** Represents a month (2628000 seconds, approximated as 30.42 days) */
    MONTH(2628000.0),
    /** Represents a year (31536000 seconds, approximated as 365 days) */
    YEAR(31536000.0);

    private final double toSecond;

    TimeUnitEnum(double toSecond) {
        this.toSecond = toSecond;
    }

    /**
     * Returns the conversion factor from this time unit to seconds.
     * 
     * @return The conversion factor to seconds
     */
    public double toSeconds() {
        return toSecond;
    }

    /**
     * Converts a string representation to the corresponding TimeUnitEnum.
     * The string is trimmed, converted to uppercase, and spaces are replaced with underscores.
     * 
     * @param str The string to convert
     * @return The corresponding TimeUnitEnum
     * @throws IllegalArgumentException if the string does not match any enum constant
     */
    public static TimeUnitEnum fromString(String str) {
        return TimeUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}
