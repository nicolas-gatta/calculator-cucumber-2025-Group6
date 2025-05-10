package unit_converter.enums;

import java.util.Optional;

public enum ConverterTypeEnum {
    LENGTH("Length"),
    VOLUME("Volume"),
    TEMPERATURE("Temperature"),
    AREA("Area"),
    DENSITY("Density"),
    CURRENCY("Currency"),
    PRESSURE("Pressure"),
    SPEED("Speed"),
    ENERGY("Energy"),
    FORCE("Force"),
    TIME("Time");

    private final String displayName;

    ConverterTypeEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Optional<ConverterTypeEnum> fromString(String str){
//        ConverterTypeEnum converterValue = ConverterTypeEnum.valueOf(str.trim().toUpperCase());
//        if(converterValue == null) {
//            throw new IllegalArgumentException("converter value not found");
//        }
//        return converterValue;
        if(str == null) return Optional.empty();
        try {
            return Optional.of(ConverterTypeEnum.valueOf(str.trim().toUpperCase()));
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }
}
