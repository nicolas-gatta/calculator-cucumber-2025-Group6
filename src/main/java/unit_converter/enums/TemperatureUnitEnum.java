package unit_converter.enums;

public enum TemperatureUnitEnum {
    CELSIUS,
    FAHRENHEIT,
    KELVIN;

    public static TemperatureUnitEnum fromSting(String str){
        return TemperatureUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }

}
