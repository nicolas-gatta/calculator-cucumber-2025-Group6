package unit_converter.enums;

public enum NumberSystemEnum {
    DECIMAL(10),
    BINARY(2),
    OCTAL(8),
    HEXADECIMAL(16);

    private final int base;

    NumberSystemEnum(int base) {
        this.base = base;
    }

    public int getBase() {
        return base;
    }

    public static NumberSystemEnum fromString(String str) {
        return NumberSystemEnum.valueOf(str.trim().toUpperCase());
    }
} 