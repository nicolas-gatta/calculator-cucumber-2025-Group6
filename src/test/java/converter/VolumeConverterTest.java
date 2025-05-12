package converter;

import org.junit.jupiter.api.Test;
import unit_converter.IUnitConverter;
import unit_converter.VolumeConverter;
import unit_converter.enums.EnumDisplayUtil;
import unit_converter.enums.VolumeUnitEnum;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class VolumeConverterTest {

    private final IUnitConverter<Double> converter = new VolumeConverter();

    @Test
    public void testUnitNamesMatchEnumValues() {

        List<String> expectedUnits = List.of(VolumeUnitEnum.values()).stream()
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());


        List<String> actualUnits = converter.getConverterUnitListNames();

        assertEquals(expectedUnits.size(), actualUnits.size(), "Number of units mismatch.");
        assertTrue(actualUnits.containsAll(expectedUnits), "Some expected units are missing.");
    }

    @Test
    public void testLiterToMilliliter() {
        double result = converter.convert("liter", "milliliter", 1.0);
        assertEquals(1000.0, result, 0.01);
    }

    @Test
    public void testGallonToLiter() {
        double result = converter.convert("gallon", "liter", 1.0);
        assertEquals(3.78541, result, 0.0001);
    }

    @Test
    public void testCupToFluidOunce() {
        double result = converter.convert("cup", "fluid_ounce", 1.0);
        assertEquals(8.115, result, 0.01);
    }

    @Test
    public void testCubicInchToMilliliter() {
        double result = converter.convert("cubic_inch", "milliliter", 1.0);
        assertEquals(16.3871, result, 0.001);
    }

    @Test
    public void testSameUnit() {
        double result = converter.convert("pint", "pint", 12.34);
        assertEquals(12.34, result, 0.0001);
    }

    @Test
    public void testCaseInsensitive() {
        double result = converter.convert("cUbIc_FooT", "liter", 1.0);
        assertEquals(28.3168, result, 0.001);
    }

    @Test
    public void testInvalidFromUnitThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("bucket", "liter", 1.0)
        );
    }

    @Test
    public void testInvalidToUnitThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert("liter", "cauldron", 1.0)
        );
    }

    @Test
    void testValueType(){
        assertEquals(Double.class, converter.getValueType(), "Value type mismatch.");
    }
}

