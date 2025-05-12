package unit_converter.enums;

/**
 * Enumeration of speed units and their conversion factors to the SI base unit: {@code meter per second (m/s)}.
 * <p>
 * Each constant represents a unit of speed, and provides a multiplier to convert
 * it into meters per second.
 * </p>
 *
 * <p>Supported units include metric, imperial, and derived speed units:</p>
 * <ul>
 *   <li>Centimeter per second, Foot per minute, Kilometer per hour, etc.</li>
 *   <li>Meter per second (SI base unit)</li>
 * </ul>
 */
public enum SpeedUnitEnum {
    CENTIMETER_PER_MINUTE(0.0001667),
    CENTIMETER_PER_SECOND(0.01),
    FOOT_PER_HOUR(0.0000846667),
    FOOT_PER_MINUTE(0.00508),
    FOOT_PER_SECOND(0.3048),
    INCH_PER_MINUTE(0.000423333),
    INCH_PER_SECOND(0.0254),
    KILOMETER_PER_HOUR(0.2777778),
    KILOMETER_PER_SECOND(1000),
    METER_PER_HOUR(0.0002777778),
    METER_PER_MINUTE(0.01667),
    METER_PER_SECOND(1.0);

    private final double toMeterPerSecond;

    SpeedUnitEnum(double toMeterPerSecond){
        this.toMeterPerSecond = toMeterPerSecond;
    }

    /**
     * Returns the conversion factor to meters per second.
     *
     * @return the equivalent of one unit in m/s
     */
    public double toMeterPerSecond(){
        return toMeterPerSecond;
    }

    /**
     * Parses a string into a {@code SpeedUnitEnum}.
     * <p>
     * This method is case-insensitive and replaces spaces with underscores,
     * allowing both standard enum names and display-friendly strings.
     * </p>
     *
     * @param str the string representing a speed unit
     * @return the corresponding {@code SpeedUnitEnum}
     * @throws IllegalArgumentException if the input is invalid
     */
    public static SpeedUnitEnum fromString(String str) {
        return SpeedUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}
