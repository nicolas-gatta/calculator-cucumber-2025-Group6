package converter;

import org.junit.jupiter.api.Test;
import unit_converter.AreaConverter;
import unit_converter.IUnitConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AreaConverterTest {

    private final IUnitConverter<Double> converter = new AreaConverter();

    //TODO: Add more unit test for others time units

    @Test
    public void testSquareMeterToSquareMileConversion() {
        double result = converter.convert( "square meter", "square mile", 100000.0);
        assertEquals(0.0386102159, result, 0.01);
    }

    @Test
    public void testInverseConversion() {
        double result = converter.convert( "square mile", "square meter", 0.0386102159);
        assertEquals(100000.0, result, 0.01);
    }

    @Test
    public void testSameUnitReturnsSameValue() {
        double value = 1.0;
        double result = converter.convert("square meter", "square meter", value);
        assertEquals(value, result, 0.0001);
    }

    @Test
    public void testCaseInsensitivity() {
        double result = converter.convert("SqUAre MetER", "SQUARE MILE", 100000.0);
        assertEquals(0.0386102159, result, 0.01);
    }

    @Test
    public void testInvalidFromUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("invalid", "SQUARE METER", 100.0)
        );
    }

    @Test
    public void testInvalidToUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("SQUARE METER", "notarealunit", 100.0)
        );
    }
}
