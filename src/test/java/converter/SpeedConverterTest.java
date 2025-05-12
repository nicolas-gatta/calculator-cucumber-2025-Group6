package converter;
import org.junit.jupiter.api.Test;
import unit_converter.IUnitConverter;
import unit_converter.SpeedConverter;
import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.SpeedUnitEnum;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class SpeedConverterTest {

    private final IUnitConverter<Double> converter = new SpeedConverter();

    @Test
    public void testUnitNamesMatchEnumValues() {

        List<String> expectedUnits = List.of(SpeedUnitEnum.values()).stream()
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());


        List<String> actualUnits = converter.getConverterUnitListNames();

        assertEquals(expectedUnits.size(), actualUnits.size(), "Number of units mismatch.");
        assertTrue(actualUnits.containsAll(expectedUnits), "Some expected units are missing.");
    }
    @Test
    public void testMeterPerSecondToKilometerPerHour() {
        double result = converter.convert("meter_per_second", "kilometer_per_hour", 1.0);
        assertEquals(3.6, result, 0.0001);
    }

    @Test
    public void testKilometerPerHourToMeterPerSecond() {
        double result = converter.convert("kilometer_per_hour", "meter_per_second", 3.6);
        assertEquals(1.0, result, 0.0001);
    }

    @Test
    public void testFootPerSecondToMeterPerSecond() {
        double result = converter.convert("foot_per_second", "meter_per_second", 1.0);
        assertEquals(0.3048, result, 0.0001);
    }

    @Test
    public void testInchPerSecondToCentimeterPerSecond() {
        double result = converter.convert("inch_per_second", "centimeter_per_second", 1.0);
        assertEquals(2.54, result, 0.0001);
    }

    @Test
    public void testKilometerPerSecondToFootPerSecond() {
        double result = converter.convert("kilometer_per_second", "foot_per_second", 1.0);
        double expected = 1000.0 / 0.3048;
        assertEquals(expected, result, 0.1);
    }

    @Test
    public void testSameUnitReturnsSameValue() {
        double result = converter.convert("meter_per_minute", "meter_per_minute", 123.45);
        assertEquals(123.45, result, 0.0001);
    }

    @Test
    public void testCaseInsensitiveUnits() {
        double result = converter.convert("KiLoMeTeR_pEr_HoUr", "mEtEr_PeR_sEcOnD", 3.6);
        assertEquals(1.0, result, 0.0001);
    }

    @Test
    public void testInvalidFromUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("mach", "meter_per_second", 1.0)
        );
    }

    @Test
    public void testInvalidToUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("meter_per_second", "warp", 1.0)
        );
    }

    @Test
    void testValueType(){
        assertEquals(Double.class, converter.getValueType(), "Value type mismatch.");
    }
}

