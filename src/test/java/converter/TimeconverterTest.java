package converter;

import org.junit.jupiter.api.Test;
import unit_converter.IUnitConverter;
import unit_converter.TimeConverter;
import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.TimeUnitEnum;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TimeconverterTest {

    private final IUnitConverter<Double> converter = new TimeConverter();

    @Test
    public void testUnitNamesMatchEnumValues() {

        List<String> expectedUnits = List.of(TimeUnitEnum.values()).stream()
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());


        List<String> actualUnits = converter.getConverterUnitListNames();

        assertEquals(expectedUnits.size(), actualUnits.size(), "Number of units mismatch.");
        assertTrue(actualUnits.containsAll(expectedUnits), "Some expected units are missing.");
    }

    @Test
    public void testMillisecondToSecondConversion() {
        double result = converter.convert( "millisecond", "second", 1000.0);
        assertEquals(1.0, result, 0.01);
    }

    @Test
    public void testSecondToMillisecondConversion() {
        double result = converter.convert( "second", "millisecond", 1.0);
        assertEquals(1000.0, result, 0.01);
    }

    @Test
    public void testSameUnitReturnsSameValue() {
        double value = 1.0;
        double result = converter.convert("second", "second", value);
        assertEquals(value, result, 0.0001);
    }

    @Test
    public void testCaseInsensitivity() {
        double result = converter.convert("SecoND", "MILLISECOND", 1.0);
        assertEquals(1000.0, result, 0.01);
    }

    @Test
    public void testInvalidFromUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("invalid", "MINUTE", 100.0)
        );
    }

    @Test
    public void testInvalidToUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("minute", "notarealunit", 100.0)
        );
    }

    @Test
    void testValueType(){
        assertEquals(Double.class, converter.getValueType(), "Value type mismatch.");
    }
}
