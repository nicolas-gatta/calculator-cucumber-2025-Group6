package converter;

import org.junit.jupiter.api.Test;
import unit_converter.ConverterFactory;
import unit_converter.*;
import unit_converter.enums.ConverterTypeEnum;
import unit_converter.enums.EnumDisplayUtil;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
class ConverterFactoryTest {

    @Test
    void testValidCreatedConverters() {
        assertInstanceOf(LengthConverter.class, ConverterFactory.getConverter("length"));
        assertInstanceOf(VolumeConverter.class, ConverterFactory.getConverter("volume"));
        assertInstanceOf(TemperatureConverter.class, ConverterFactory.getConverter("temperature"));
        assertInstanceOf(AreaConverter.class, ConverterFactory.getConverter("area"));
        assertInstanceOf(DensityConverter.class, ConverterFactory.getConverter("density"));
        assertInstanceOf(CurrencyConverter.class, ConverterFactory.getConverter("currency"));
        assertInstanceOf(PressureConverter.class, ConverterFactory.getConverter("pressure"));
        assertInstanceOf(SpeedConverter.class, ConverterFactory.getConverter("speed"));
        assertInstanceOf(EnergyConverter.class, ConverterFactory.getConverter("energy"));
        assertInstanceOf(TimeConverter.class, ConverterFactory.getConverter("time"));
        assertInstanceOf(ForceConverter.class, ConverterFactory.getConverter("force"));
    }

    @Test
    public void testAvailableConverterListNamesMatchEnum() {
        List<String> expectedNames = List.of(ConverterTypeEnum.values()).stream()
                .map(Enum::name)
                .map(EnumDisplayUtil::toDisplayName)
                .collect(Collectors.toList());

        List<String> actualNames = ConverterFactory.getConverterListNames();

        assertEquals(expectedNames.size(), actualNames.size(), "Mismatch in number of available converters.");
        assertTrue(actualNames.containsAll(expectedNames), "Some expected converter names are missing.");
    }


    @Test
    void testCaseInsensitiveInput() {
        assertInstanceOf(LengthConverter.class, ConverterFactory.getConverter("LeNgTh"));
        assertInstanceOf(TemperatureConverter.class, ConverterFactory.getConverter("TEMPERATURE"));
        assertInstanceOf(SpeedConverter.class, ConverterFactory.getConverter("sPeEd"));
    }

    @Test
    void testInvalidConverterNameReturnsNull() {
        assertNull(ConverterFactory.getConverter("invalid"));
        assertNull(ConverterFactory.getConverter(""));
        assertNull(ConverterFactory.getConverter(null));

    }
}
