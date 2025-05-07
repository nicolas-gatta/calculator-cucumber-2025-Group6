package converter;

import org.junit.jupiter.api.Test;
import unit_converter.ConverterFactory;
import unit_converter.*;

import static org.junit.jupiter.api.Assertions.*;
public class ConverterFactoryTest {

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
