package converter;
import org.junit.jupiter.api.Test;
import unit_converter.IUnitConverter;
import unit_converter.LengthConverter;
import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.LengthUnitEnum;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class LengthConverterTest {

    private final IUnitConverter<Double> converter = new LengthConverter();

    @Test
    public void testUnitNamesMatchEnumValues() {

        List<String> expectedUnits = List.of(LengthUnitEnum.values()).stream()
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());


        List<String> actualUnits = converter.getConverterUnitListNames();

        assertEquals(expectedUnits.size(), actualUnits.size(), "Number of units mismatch.");
        assertTrue(actualUnits.containsAll(expectedUnits), "Some expected units are missing.");
    }

    @Test
    public void testMeterToCentimeter() {
        double result = converter.convert("meter", "centimeter", 1.0);
        assertEquals(100.0, result, 0.0001);
    }

    @Test
    public void testMileToKilometer() {
        double result = converter.convert("mile", "kilometer", 1.0);
        assertEquals(1.609344, result, 0.0001);
    }

    @Test
    public void testFootToInch() {
        double result = converter.convert("foot", "inch", 1.0);
        assertEquals(12.0, result, 0.0001);
    }

    @Test
    public void testSameUnitReturnsSameValue() {
        double result = converter.convert("yard", "yard", 42.0);
        assertEquals(42.0, result, 0.0001);
    }

    @Test
    public void testCaseInsensitiveUnits() {
        double result = converter.convert("Nautical_Mile", "METER", 1.0);
        assertEquals(1852.0, result, 0.001);
    }

    @Test
    public void testInvalidFromUnitThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("lightyear", "meter", 1.0)
        );
    }

    @Test
    public void testInvalidToUnitThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("meter", "hand", 1.0)
        );
    }

    @Test
    void testValueType(){
        assertEquals(Double.class, converter.getValueType(), "Value type mismatch.");
    }
}

