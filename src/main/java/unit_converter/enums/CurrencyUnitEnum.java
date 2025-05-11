package unit_converter.enums;

/**
 * Enumeration of supported currencies for currency conversion.
 * <p>
 * Each currency constant stores its relative value to 1 Euro, allowing
 * bidirectional conversion between the defined currencies.
 * </p>
 *
 * Example: 1 EUR = 1.08 USD (as defined in this enum)
 */
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

    /**
     * Returns the conversion factor to euros.
     *
     * @return the equivalent of 1 unit of this currency in euros
     */
    public double toEuro() {
        return toEuro;
    }

    /**
     * Returns the enum value that matches the input string (case-insensitive).
     *
     * @param str the currency name (e.g., "eur", "USD")
     * @return the matching {@code CurrencyUnitEnum}
     * @throws IllegalArgumentException if the string doesn't match any constant
     */
    public static CurrencyUnitEnum fromString(String str) {
        return CurrencyUnitEnum.valueOf(str.trim().toUpperCase());
    }
}
