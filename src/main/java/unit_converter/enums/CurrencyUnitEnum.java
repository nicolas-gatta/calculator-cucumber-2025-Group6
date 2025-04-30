package unit_converter.enums;

public enum CurrencyUnitEnum {
    EUR(1.0),
    USD(1.08),
    GBP(0.86),
    JPY(162.0),
    CHF(0.98),
    CAD(1.46),
    AUD(1.65);

    private final double toEuro;

    CurrencyUnitEnum(double toEuro) {
        this.toEuro = toEuro;
    }

    public double toEuro() {
        return toEuro;
    }

    public static CurrencyUnitEnum fromString(String str) {
        return CurrencyUnitEnum.valueOf(str.trim().toUpperCase());
    }
}
