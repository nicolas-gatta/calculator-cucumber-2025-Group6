package converter;

import org.junit.jupiter.api.Test;
import unit_converter.DensityConverter;
import unit_converter.IUnitConverter;
import unit_converter.enums.DensityUnitEnum;
import unit_converter.enums.EnumDisplayUtil;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class DensityConverterTest {

    private final IUnitConverter<Double> converter = new DensityConverter();

    @Test
    public void testUnitNamesMatchEnumValues() {

        List<String> expectedUnits = List.of(DensityUnitEnum.values()).stream()
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());


        List<String> actualUnits = converter.getConverterUnitListNames();

        assertEquals(expectedUnits.size(), actualUnits.size(), "Number of units mismatch.");
        assertTrue(actualUnits.containsAll(expectedUnits), "Some expected units are missing.");
    }

    @Test
    public void testGramPerCm3ToKgPerM3() {
        double result = converter.convert("gram per cubic centimeter", "kilogram per cubic meter", 1.0);
        assertEquals(1000.0, result, 0.001);
    }

    @Test
    public void testKgPerM3ToGramPerCm3() {
        double result = converter.convert("kilogram per cubic meter", "gram per cubic centimeter", 1000.0);
        assertEquals(1.0, result, 0.001);
    }

    @Test
    public void testLbPerFt3ToKgPerM3() {
        double result = converter.convert("pound per cubic foot", "kilogram per cubic meter", 1.0);
        assertEquals(16.0185, result, 0.001);
    }

    @Test
    public void testKgPerM3ToLbPerFt3() {
        double result = converter.convert( "kilogram per cubic meter", "pound per cubic foot", 16.0185);
        assertEquals(1.0, result, 0.001);
    }

    @Test
    public void testSameUnitReturnsSameValue() {
        double value = 750.0;
        double result = converter.convert("gram per liter", "gram per liter", value);
        assertEquals(value, result, 0.0001);
    }

    @Test
    public void testCaseInsensitivity() {
        double result = converter.convert( "GrAm PeR CuBiC CeNtImEtEr", "KILOGRAM PER CUBIC METER", 1.0);
        assertEquals(1000.0, result, 0.001);
    }

    @Test
    public void testInvalidUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("unknown unit", "kilogram per cubic meter", 1.0)
        );
    }

    @Test
    void testValueType(){
        assertEquals(Double.class, converter.getValueType(), "Value type mismatch.");
    }
}
