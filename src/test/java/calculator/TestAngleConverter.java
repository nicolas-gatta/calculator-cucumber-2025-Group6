package calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestAngleConverter {

    private static final double DELTA = 1e-10;

    @Test
    void testDegreesToRadians() {
        assertEquals(Math.PI, AngleConverter.degreesToRadians(180.0), DELTA);
        assertEquals(Math.PI/2, AngleConverter.degreesToRadians(90.0), DELTA);
        assertEquals(0.0, AngleConverter.degreesToRadians(0.0), DELTA);
    }

    @Test
    void testRadiansToDegrees() {
        assertEquals(180.0, AngleConverter.radiansToDegrees(Math.PI), DELTA);
        assertEquals(90.0, AngleConverter.radiansToDegrees(Math.PI/2), DELTA);
        assertEquals(0.0, AngleConverter.radiansToDegrees(0.0), DELTA);
    }

    @Test
    void testConversionRoundTrip() {
        double initialDegrees = 45.0;
        double radians = AngleConverter.degreesToRadians(initialDegrees);
        double finalDegrees = AngleConverter.radiansToDegrees(radians);
        assertEquals(initialDegrees, finalDegrees, DELTA);
    }
} 