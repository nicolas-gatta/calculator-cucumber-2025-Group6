package unit_converter.enums;

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

    public double toSeconds() {
        return toSecond;
    }

    public static TimeUnitEnum fromString(String str) {
        return TimeUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}
