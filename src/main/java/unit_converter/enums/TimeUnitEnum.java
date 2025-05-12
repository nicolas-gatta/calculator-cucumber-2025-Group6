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
    NANOSECOND(0.000000001),
    MILLISECOND(0.001),
    SECOND(1.0),
    MINUTE(60.0),
    HOUR(3600.0),
    DAY(86400.0),
    WEEK(604800.0),
    MONTH(2628000.0),
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
