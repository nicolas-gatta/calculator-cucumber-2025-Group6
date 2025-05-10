package unit_converter.enums;

public class EnumDisplayUtil {

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
