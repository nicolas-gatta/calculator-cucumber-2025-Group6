package converter;

import org.junit.jupiter.api.Test;
import unit_converter.IUnitConverter;
import unit_converter.PressureConverter;

import static org.junit.jupiter.api.Assertions.*;

public class PressureConverterTest {

    private final IUnitConverter<Double> converter = new PressureConverter();

    @Test
    public void testAtmToPsi() {
        double result = converter.convert("atmosphere", "psi", 1.0);
        assertEquals(14.6959, result, 0.01);
    }

    @Test
    public void testBarToPascal() {
        double result = converter.convert("bar", "pascal", 1.0);
        assertEquals(100000.0, result, 0.001);
    }

    @Test
    public void testHectoPascalToTorr() {
        double result = converter.convert("hectopascal", "torr", 1.0);
        double expected = 100.0 / 133.322;
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testSameUnit() {
        double value = 1234.56;
        double result = converter.convert("psi", "psi", value);
        assertEquals(value, result, 0.0001);
    }

    @Test
    public void testInvalidFromUnitThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("invalid", "psi", 10.0)
        );
    }

    @Test
    public void testInvalidToUnitThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("atm", "whoops", 10.0)
        );
    }
}

