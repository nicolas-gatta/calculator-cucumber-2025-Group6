package converter;

import org.junit.jupiter.api.Test;
import unit_converter.IUnitConverter;
import unit_converter.TemperatureConverter;
import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.TemperatureUnitEnum;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TemperatureConverterTest {

    private final IUnitConverter<Double> converter = new TemperatureConverter();

    @Test
    public void testUnitNamesMatchEnumValues() {

        List<String> expectedUnits = List.of(TemperatureUnitEnum.values()).stream()
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());


        List<String> actualUnits = converter.getConverterUnitListNames();

        assertEquals(expectedUnits.size(), actualUnits.size(), "Number of units mismatch.");
        assertTrue(actualUnits.containsAll(expectedUnits), "Some expected units are missing.");
    }

    @Test
    public void testCelsiusToFahrenheit() {
        double result = converter.convert("celsius", "fahrenheit", 100.0);
        assertEquals(212.0, result, 0.0001);
    }

    @Test
    public void testFahrenheitToCelsius() {
        double result = converter.convert("fahrenheit", "celsius", 32.0);
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void testCelsiusToKelvin() {
        double result = converter.convert("celsius", "kelvin", 0.0);
        assertEquals(273.15, result, 0.0001);
    }

    @Test
    public void testKelvinToCelsius() {
        double result = converter.convert("kelvin", "celsius", 273.15);
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void testFahrenheitToKelvin() {
        double result = converter.convert("fahrenheit", "kelvin", 32.0);
        assertEquals(273.15, result, 0.0001);
    }

    @Test
    public void testKelvinToFahrenheit() {
        double result = converter.convert("kelvin", "fahrenheit", 273.15);
        assertEquals(32.0, result, 0.0001);
    }

    @Test
    public void testSameUnitReturnsSameValue() {
        double result = converter.convert("celsius", "celsius", 123.456);
        assertEquals(123.456, result, 0.0001);
    }

    @Test
    public void testCaseInsensitivity() {
        double result = converter.convert("CeLsIuS", "kElViN", 0.0);
        assertEquals(273.15, result, 0.0001);
    }

    @Test
    public void testInvalidUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("celsius", "unknown", 100.0)
        );
    }
}
