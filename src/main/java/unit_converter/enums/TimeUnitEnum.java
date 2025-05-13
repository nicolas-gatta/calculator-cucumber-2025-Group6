package unit_converter.enums;

/**
 * Enumeration of time units and their conversion factors to the SI base unit: {@code second (s)}.
 * <p>
 * Each constant defines a time unit and its equivalent in seconds. This enum is used by the
 * {@code TimeConverter} to perform time-based conversions between various granularities,
 * from nanoseconds to years.
 * </p>
 *
 * <p>Supported units include:</p>
 * <ul>
 *   <li>Nanosecond, Millisecond, Second, Minute, Hour</li>
 *   <li>Day, Week, Month, Year</li>
 * </ul>
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
     * Returns the equivalent value of one unit in seconds.
     *
     * @return conversion factor to seconds
     */
    public double toSeconds() {
        return toSecond;
    }

    /**
     * Parses a string into a {@code TimeUnitEnum}.
     * <p>
     * Matching is case-insensitive and accepts names with or without spaces.
     * </p>
     *
     * @param str the string representation (e.g., "minute", "NANOSECOND")
     * @return the matching {@code TimeUnitEnum}
     * @throws IllegalArgumentException if no matching unit is found
     */
    public static TimeUnitEnum fromString(String str) {
        return TimeUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}
