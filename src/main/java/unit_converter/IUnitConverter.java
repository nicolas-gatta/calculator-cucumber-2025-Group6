package unit_converter;

import java.util.List;

public interface IUnitConverter<T>{
    T convert(String fromUnit, String toUnit, T value);
    //List<String> getConverterUnitListNames();
    //T convert(U from, U to, T value);
}
