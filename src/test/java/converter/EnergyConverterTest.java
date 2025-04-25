package converter;
import org.junit.jupiter.api.Test;
import unit_converter.EnergyConverter;
import unit_converter.IUnitConverter;

import static org.junit.jupiter.api.Assertions.*;

public class EnergyConverterTest {

    private final IUnitConverter<Double> converter = new EnergyConverter();

    @Test
    public void testKwhToJoule() {
        double result = converter.convert("kilowatt_hour", "joule", 1.0);
        assertEquals(3600000.0, result, 0.01);
    }

    @Test
    public void testCalorieToJoule() {
        double result = converter.convert("calorie", "joule", 1.0);
        assertEquals(4.184, result, 0.0001);
    }

    @Test
    public void testJouleToKilojoule() {
        double result = converter.convert("joule", "kilojoule", 1000.0);
        assertEquals(1.0, result, 0.0001);
    }

    @Test
    public void testErgToJoule() {
        double result = converter.convert("erg", "joule", 1e7);
        assertEquals(1.0, result, 0.00001);
    }

    @Test
    public void testSameUnitReturnsSameValue() {
        double result = converter.convert("btu", "btu", 99.99);
        assertEquals(99.99, result, 0.0001);
    }

    @Test
    public void testCaseInsensitiveConversion() {
        double result = converter.convert("KilOCalOriE", "KILOJOULE", 1.0);
        assertEquals(4.184, result, 0.01);
    }

    @Test
    public void testInvalidFromUnitThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("electronvolt", "joule", 1.0)
        );
    }

    @Test
    public void testInvalidToUnitThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("joule", "banana", 1.0)
        );
    }
}
