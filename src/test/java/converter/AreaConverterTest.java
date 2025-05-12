package converter;

import org.junit.jupiter.api.Test;
import unit_converter.AreaConverter;
import unit_converter.IUnitConverter;
import unit_converter.enums.AreaUnitEnum;
import unit_converter.enums.EnumDisplayUtil;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class AreaConverterTest {

    private final IUnitConverter<Double> converter = new AreaConverter();

    @Test
    public void testUnitNamesMatchEnumValues() {

        List<String> expectedUnits = List.of(AreaUnitEnum.values()).stream()
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());


        List<String> actualUnits = converter.getConverterUnitListNames();

        assertEquals(expectedUnits.size(), actualUnits.size(), "Number of units mismatch.");
        assertTrue(actualUnits.containsAll(expectedUnits), "Some expected units are missing.");
    }

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
