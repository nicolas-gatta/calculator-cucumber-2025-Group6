package unit_converter.enums;

import java.util.Optional;

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
    TIME;

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
