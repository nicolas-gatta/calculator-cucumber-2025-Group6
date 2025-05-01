package converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unit_converter.NumberSystemConverter;

import static org.junit.jupiter.api.Assertions.*;

class NumberSystemConverterTest {

    private NumberSystemConverter converter;

    @BeforeEach
    void setUp() {
        converter = new NumberSystemConverter();
    }

    @Test
    void testDecimalToBinary() {
        assertEquals("0b1010", converter.convert("DECIMAL", "BINARY", "10"));
        assertEquals("0b11111111", converter.convert("DECIMAL", "BINARY", "255"));
        assertEquals("0b0", converter.convert("DECIMAL", "BINARY", "0"));
        assertEquals("0b1111111111111111111111111111111", converter.convert("DECIMAL", "BINARY", "2147483647"));
    }

    @Test
    void testDecimalToOctal() {
        assertEquals("012", converter.convert("DECIMAL", "OCTAL", "10"));
        assertEquals("0377", converter.convert("DECIMAL", "OCTAL", "255"));
        assertEquals("00", converter.convert("DECIMAL", "OCTAL", "0"));
        assertEquals("017777777777", converter.convert("DECIMAL", "OCTAL", "2147483647"));
    }

    @Test
    void testDecimalToHexadecimal() {
        assertEquals("0xA", converter.convert("DECIMAL", "HEXADECIMAL", "10"));
        assertEquals("0xFF", converter.convert("DECIMAL", "HEXADECIMAL", "255"));
        assertEquals("0x0", converter.convert("DECIMAL", "HEXADECIMAL", "0"));
        assertEquals("0x7FFFFFFF", converter.convert("DECIMAL", "HEXADECIMAL", "2147483647"));
    }

    @Test
    void testBinaryToDecimal() {
        assertEquals("10", converter.convert("BINARY", "DECIMAL", "1010"));
        assertEquals("10", converter.convert("BINARY", "DECIMAL", "0b1010"));
        assertEquals("255", converter.convert("BINARY", "DECIMAL", "11111111"));
        assertEquals("0", converter.convert("BINARY", "DECIMAL", "0"));
    }

    @Test
    void testOctalToDecimal() {
        assertEquals("10", converter.convert("OCTAL", "DECIMAL", "12"));
        assertEquals("10", converter.convert("OCTAL", "DECIMAL", "012"));
        assertEquals("255", converter.convert("OCTAL", "DECIMAL", "377"));
        assertEquals("0", converter.convert("OCTAL", "DECIMAL", "0"));
    }

    @Test
    void testHexadecimalToDecimal() {
        assertEquals("10", converter.convert("HEXADECIMAL", "DECIMAL", "A"));
        assertEquals("10", converter.convert("HEXADECIMAL", "DECIMAL", "0xA"));
        assertEquals("255", converter.convert("HEXADECIMAL", "DECIMAL", "FF"));
        assertEquals("0", converter.convert("HEXADECIMAL", "DECIMAL", "0"));
    }

    @Test
    void testBinaryToHexadecimal() {
        assertEquals("0xA", converter.convert("BINARY", "HEXADECIMAL", "1010"));
        assertEquals("0xFF", converter.convert("BINARY", "HEXADECIMAL", "11111111"));
    }

    @Test
    void testHexadecimalToBinary() {
        assertEquals("0b1010", converter.convert("HEXADECIMAL", "BINARY", "A"));
        assertEquals("0b11111111", converter.convert("HEXADECIMAL", "BINARY", "FF"));
    }

    @Test
    void testOctalToBinary() {
        assertEquals("0b1010", converter.convert("OCTAL", "BINARY", "12"));
        assertEquals("0b11111111", converter.convert("OCTAL", "BINARY", "377"));
    }

    @Test
    void testBinaryToOctal() {
        assertEquals("012", converter.convert("BINARY", "OCTAL", "1010"));
        assertEquals("0377", converter.convert("BINARY", "OCTAL", "11111111"));
    }

    @Test
    void testHexadecimalToOctal() {
        assertEquals("012", converter.convert("HEXADECIMAL", "OCTAL", "A"));
        assertEquals("0377", converter.convert("HEXADECIMAL", "OCTAL", "FF"));
    }

    @Test
    void testOctalToHexadecimal() {
        assertEquals("0xA", converter.convert("OCTAL", "HEXADECIMAL", "12"));
        assertEquals("0xFF", converter.convert("OCTAL", "HEXADECIMAL", "377"));
    }

    @Test
    void testInvalidInput() {
        // Test with invalid binary input
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            converter.convert("BINARY", "DECIMAL", "12A"));
        assertTrue(exception.getMessage().contains("Invalid number format"));

        // Test with invalid hexadecimal input
        exception = assertThrows(IllegalArgumentException.class, () -> 
            converter.convert("HEXADECIMAL", "DECIMAL", "XYZ"));
        assertTrue(exception.getMessage().contains("Invalid number format"));
    }

    @Test
    void testEmptyInput() {
        assertEquals("0", converter.convert("DECIMAL", "BINARY", ""));
        assertEquals("0", converter.convert("BINARY", "DECIMAL", ""));
    }

    @Test
    void testWithPrefixes() {
        // Test with prefixes
        assertEquals("0b1010", converter.convert("DECIMAL", "BINARY", "10"));
        assertEquals("10", converter.convert("BINARY", "DECIMAL", "0b1010"));
        assertEquals("0xFF", converter.convert("DECIMAL", "HEXADECIMAL", "255"));
        assertEquals("255", converter.convert("HEXADECIMAL", "DECIMAL", "0xFF"));
    }

    @Test
    void testSameBaseConversion() {
        // Converting to the same base should return the same number (possibly with formatting)
        assertEquals("42", converter.convert("DECIMAL", "DECIMAL", "42"));
        assertEquals("0b101010", converter.convert("BINARY", "BINARY", "101010"));
        assertEquals("0xFF", converter.convert("HEXADECIMAL", "HEXADECIMAL", "FF"));
    }

    @Test
    void testLargeNumbers() {
        // Test with large numbers
        assertEquals("0b1111111111111111111111111111111", converter.convert("DECIMAL", "BINARY", "2147483647"));
        assertEquals("2147483647", converter.convert("BINARY", "DECIMAL", "1111111111111111111111111111111"));
        assertEquals("0x7FFFFFFF", converter.convert("DECIMAL", "HEXADECIMAL", "2147483647"));
        assertEquals("2147483647", converter.convert("HEXADECIMAL", "DECIMAL", "7FFFFFFF"));
    }
}