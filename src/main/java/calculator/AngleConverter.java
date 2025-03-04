package calculator;

/**
 * Utility class for converting angles between degrees and radians.
 */
public class AngleConverter {
    
    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private AngleConverter() {
        // Utility class should not be instantiated
    }

    /**
     * Converts an angle from degrees to radians
     * @param degrees The angle in degrees
     * @return The angle in radians
     */
    public static double degreesToRadians(double degrees) {
        return Math.toRadians(degrees);
    }
    
    /**
     * Converts an angle from radians to degrees
     * @param radians The angle in radians
     * @return The angle in degrees
     */
    public static double radiansToDegrees(double radians) {
        return Math.toDegrees(radians);
    }
}