package converter;

import org.junit.jupiter.api.Test;
import unit_converter.CurrencyConverter;
import unit_converter.IUnitConverter;
import unit_converter.enums.CurrencyUnitEnum;
import unit_converter.enums.EnumDisplayUtil;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyConverterTest {

    private final IUnitConverter<Double> converter = new CurrencyConverter();

    @Test
    public void testUnitNamesMatchEnumValues() {

        List<String> expectedUnits = List.of(CurrencyUnitEnum.values()).stream()
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());


        List<String> actualUnits = converter.getConverterUnitListNames();

        assertEquals(expectedUnits.size(), actualUnits.size(), "Number of units mismatch.");
        assertTrue(actualUnits.containsAll(expectedUnits), "Some expected units are missing.");
    }

    @Test
    public void testEurToUsd() {
        double result = converter.convert("eur", "usd", 1.0);
        assertEquals(1.08, result, 0.0001);
    }

    @Test
    public void testUsdToEur() {
        double result = converter.convert("usd", "eur", 1.08);
        assertEquals(1.0, result, 0.0001);
    }

    @Test
    public void testEurToJpy() {
        double result = converter.convert("eur", "jpy", 1.0);
        assertEquals(162.0, result, 0.0001);
    }

    @Test
    public void testJpyToEur() {
        double result = converter.convert("jpy", "eur", 162.0);
        assertEquals(1.0, result, 0.0001);
    }

    @Test
    public void testGbpToChf() {
        // 1 EUR = 0.86 GBP → 1 GBP = 1 / 0.86 EUR
        // 1 EUR = 0.98 CHF → Résultat attendu : (1 / 0.86) * 0.98
        double result = converter.convert("gbp", "chf", 0.86); // 0.86 GBP = 1 EUR
        assertEquals(0.98, result, 0.0001);
    }

    @Test
    public void testAudToCad() {
        double result = converter.convert("aud", "cad", 1.65); // 1.65 AUD = 1 EUR
        assertEquals(1.46, result, 0.0001); // 1 EUR = 1.46 CAD
    }

    @Test
    public void testSameUnitReturnsSameValue() {
        double result = converter.convert("cad", "cad", 42.42);
        assertEquals(42.42, result, 0.0001);
    }

    @Test
    public void testCaseInsensitivity() {
        double result = converter.convert("AuD", "cAd", 1.0);
        double expected = 1.0 / 1.65 * 1.46; // AUD → EUR → CAD
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testInvalidFromUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("btc", "eur", 10.0)
        );
    }

    @Test
    public void testInvalidToUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("eur", "dogecoin", 10.0)
        );
    }
}

