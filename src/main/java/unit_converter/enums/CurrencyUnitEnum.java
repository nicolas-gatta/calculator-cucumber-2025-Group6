package unit_converter.enums;

/**
 * Enum representing different currency units with their conversion rates to Euro.
 */
public enum CurrencyUnitEnum {
    /**
     * Euro (EUR) - Base currency with rate 1.0
     */
    EUR(1.0),
    
    /**
     * US Dollar (USD) with conversion rate to Euro
     */
    USD(1.08),
    
    /**
     * British Pound (GBP) with conversion rate to Euro
     */
    GBP(0.86),
    
    /**
     * Japanese Yen (JPY) with conversion rate to Euro
     */
    JPY(162.0),
    
    /**
     * Swiss Franc (CHF) with conversion rate to Euro
     */
    CHF(0.98),
    
    /**
     * Canadian Dollar (CAD) with conversion rate to Euro
     */
    CAD(1.46),
    
    /**
     * Australian Dollar (AUD) with conversion rate to Euro
     */
    AUD(1.65);

    private final double toEuro;

    CurrencyUnitEnum(double toEuro) {
        this.toEuro = toEuro;
    }

    /**
     * Returns the conversion rate from this currency to Euro.
     * 
     * @return the conversion rate to Euro
     */
    public double toEuro() {
        return toEuro;
    }

    /**
     * Converts a string representation to the corresponding CurrencyUnitEnum value.
     * 
     * @param str the string to convert
     * @return the matching CurrencyUnitEnum
     */
    public static CurrencyUnitEnum fromString(String str) {
        return CurrencyUnitEnum.valueOf(str.trim().toUpperCase());
    }
}
