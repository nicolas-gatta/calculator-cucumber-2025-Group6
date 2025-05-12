package unit_converter;

import unit_converter.enums.CurrencyUnitEnum;

/**
 * Converter for currency units.
 * Implements the IUnitConverter interface for handling currency conversions.
 */
public class CurrencyConverter implements IUnitConverter<Double>{
    
    /**
     * Default constructor for CurrencyConverter.
     */
    public CurrencyConverter() {
        // Default constructor
    }
    
    @Override
    public Double convert(String fromUnit, String toUnit, Double value) {
        CurrencyUnitEnum from = CurrencyUnitEnum.fromString(fromUnit);
        CurrencyUnitEnum to = CurrencyUnitEnum.fromString(toUnit);
        return convert(from, to, value);
    }

    /**
     * Converts a value from one currency unit to another.
     * 
     * @param from the source currency unit
     * @param to the target currency unit
     * @param value the amount to convert
     * @return the converted amount in the target currency
     */
    public Double convert(CurrencyUnitEnum from, CurrencyUnitEnum to, Double value){
        double inEuro = value / from.toEuro();
        return inEuro * to.toEuro();
    }
}
