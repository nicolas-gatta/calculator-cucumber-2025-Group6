package converter;

import org.junit.jupiter.api.Test;
import unit_converter.enums.EnumDisplayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumDisplayUtilTest {

    @Test
    void testDisplaySingleWord() {
        String input = "KILOMETER";
        String expected = "Kilometer";
        assertEquals(expected, EnumDisplayUtil.toDisplayName(input));
    }

    @Test
    void testDisplayWithUnderscores() {
        String input = "SQUARE_METER";
        String expected = "Square Meter";
        assertEquals(expected, EnumDisplayUtil.toDisplayName(input));
    }

    @Test
    void testDisplayMixedCaseWithSpaces() {
        String input = "   fluid_OUNCE ";
        String expected = "Fluid Ounce";
        assertEquals(expected, EnumDisplayUtil.toDisplayName(input));
    }

    @Test
    void testDisplayNullInput() {
        assertEquals("", EnumDisplayUtil.toDisplayName(null));
    }

    @Test
    void testDisplayBlankInput() {
        assertEquals("", EnumDisplayUtil.toDisplayName("   "));
    }

    @Test
    void testDisplayMultipleUnderscores() {
        String input = "LIGHT_YEAR_DISTANCE";
        String expected = "Light Year Distance";
        assertEquals(expected, EnumDisplayUtil.toDisplayName(input));
    }


//    @Test
//    void testPrivateConstructorThrowsException() {
//        Constructor<?> constructor = EnumDisplayUtil.class.getDeclaredConstructors()[0];
//        constructor.setAccessible(true);
//        Exception exception = assertThrows(InvocationTargetException.class, () -> constructor.newInstance());
//        assertTrue(exception.getCause() instanceof IllegalStateException);
//        assertEquals("Illegal stat EnumDisplayUtil", exception.getCause().getMessage());
//    }
}
