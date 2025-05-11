package unit_converter.enums;

import java.util.Optional;

/**
 * Enumeration of all available converter types in the application.
 * <p>
 * Each constant represents a category of physical or logical units
 * that can be converted (e.g., LENGTH, ENERGY, BINARY).
 * </p>
 *
 * This enum is primarily used to route requests to the correct
 * {@link unit_converter.IUnitConverter} via the {@code ConverterFactory}.
 */
public enum ConverterTypeEnum {
    LENGTH,
    VOLUME,
    TEMPERATURE,
    AREA,
    DENSITY,
    CURRENCY,
    PRESSURE,
    SPEED,
    ENERGY,
    FORCE,
    TIME,
    BINARY;

    /**
     * Returns an optional enum value matching the given string.
     * <p>
     * The lookup is case-insensitive and safely wrapped in an {@link Optional}.
     * </p>
     *
     * @param str the string to match (e.g., "length", "ENERGY")
     * @return an Optional containing the matched enum, or empty if not found
     */
    public static Optional<ConverterTypeEnum> fromString(String str){
        if(str == null) return Optional.empty();
        try {
            return Optional.of(ConverterTypeEnum.valueOf(str.trim().toUpperCase()));
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }
}
