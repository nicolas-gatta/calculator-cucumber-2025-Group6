package converter;

import org.junit.jupiter.api.Test;
import unit_converter.IUnitConverter;
import unit_converter.PressureConverter;
import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.PressureUnitEnum;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class PressureConverterTest {

    private final IUnitConverter<Double> converter = new PressureConverter();

    @Test
    public void testUnitNamesMatchEnumValues() {

        List<String> expectedUnits = List.of(PressureUnitEnum.values()).stream()
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());


        List<String> actualUnits = converter.getConverterUnitListNames();

        assertEquals(expectedUnits.size(), actualUnits.size(), "Number of units mismatch.");
        assertTrue(actualUnits.containsAll(expectedUnits), "Some expected units are missing.");
    }

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

    @Test
    void testValueType(){
        assertEquals(Double.class, converter.getValueType(), "Value type mismatch.");
    }
}

