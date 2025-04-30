package unit_converter;

public interface IUnitConverter<T>{
    T convert(String fromUnit, String toUnit, T value);
    //T convert(U from, U to, T value);
}
