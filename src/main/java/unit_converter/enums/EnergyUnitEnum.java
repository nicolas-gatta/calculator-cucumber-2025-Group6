package unit_converter.enums;

public enum EnergyUnitEnum {
    ELECTRON_VOLT(1.60218E-19),
    JOULE(1.0),
    KILOJOULE(1000.0),
    MEGAJOULE(1000000.0),
    CALORIE(4.184),
    KILOCALORIE(4184.0),
    WATT_HOUR(3600.0),
    KILOWATT_HOUR(3600000.0),
    BTU(1055.06),
    ERG(1e-7);

    private final double toJoule;

    EnergyUnitEnum(double toJoule) {
        this.toJoule = toJoule;
    }

    public double toJoule() {
        return toJoule;
    }

    public static EnergyUnitEnum fromString(String str) {
        return EnergyUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}
