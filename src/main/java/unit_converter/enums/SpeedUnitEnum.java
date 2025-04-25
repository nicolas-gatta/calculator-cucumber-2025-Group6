package unit_converter.enums;

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

    public double toMeterPerSecond(){
        return toMeterPerSecond;
    }

    public static SpeedUnitEnum fromString(String str) {
        return SpeedUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}
