package unit_converter;

public interface IUnitConverter<T>{
    T convert(String fromUnit, String toUnit, T value);
}
