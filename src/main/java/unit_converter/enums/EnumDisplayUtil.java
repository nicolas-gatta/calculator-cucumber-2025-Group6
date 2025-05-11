package unit_converter.enums;

/**
 * Utility class for formatting enum names into human-readable display strings.
 * <p>
 * Converts enum constant names (e.g., {@code "SQUARE_METER"}) into more readable
 * strings (e.g., {@code "Square Meter"}) by applying proper casing and spacing.
 * This class is typically used for CLI display or UI labels.
 * </p>
 *
 * <p>This class is stateless and should not be instantiated.</p>
 */
public class EnumDisplayUtil {

    private EnumDisplayUtil(){
        throw new IllegalStateException("Illegal stat EnumDisplayUtil");
    }

    /**
     * Converts an enum name (in ALL_CAPS_WITH_UNDERSCORES) to a display-friendly format.
     * <p>
     * For example: {@code "SQUARE_METER"} → {@code "Square Meter"}.
     * </p>
     *
     * @param enumValue the raw enum name
     * @return a formatted string for display purposes, or an empty string if input is null or blank
     */
    public static String toDisplayName(String enumValue) {
        if (enumValue == null || enumValue.isBlank()) return "";

        String withSpaces = enumValue.trim().replaceAll("_", " ").toLowerCase();

        String[] words = withSpaces.split(" ");
        StringBuilder displayName = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                displayName.append(Character.toUpperCase(words[i].charAt(0)))
                        .append(words[i].substring(1));
                if (i < words.length - 1) displayName.append(" ");
            }
        }

        return displayName.toString();
    }
}
