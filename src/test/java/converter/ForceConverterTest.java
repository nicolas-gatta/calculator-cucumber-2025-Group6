package converter;

import org.junit.jupiter.api.Test;
import unit_converter.ForceConverter;
import unit_converter.IUnitConverter;

import static org.junit.jupiter.api.Assertions.*;

public class ForceConverterTest {

    private final IUnitConverter<Double> converter = new ForceConverter();

    //TODO: Add more unit test for others time units

    @Test
    public void testNewtonToKilopondConversion() {
        double result = converter.convert( "Newton", "Kilopond", 100.0);
        assertEquals(10.1971621, result, 0.01);
    }

    @Test
    public void testSecondToMillisecondConversion() {
        double result = converter.convert( "Kilopond", "Newton", 10.1971621);
        assertEquals(100.0, result, 0.01);
    }

    @Test
    public void testSameUnitReturnsSameValue() {
        double value = 10.0;
        double result = converter.convert("Kilopond", "Kilopond", value);
        assertEquals(value, result, 0.0001);
    }

    @Test
    public void testCaseInsensitivity() {
        double result = converter.convert("KiLo FoRCE", "POUNDAL", 4.0);
        assertEquals(283.726448, result, 0.01);
    }

    @Test
    public void testInvalidFromUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("invalid", "Newton", 100.0)
        );
    }

    @Test
    public void testInvalidToUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("Newton", "notarealunit", 100.0)
        );
    }
}
