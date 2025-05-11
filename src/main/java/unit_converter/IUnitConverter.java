package unit_converter;

import java.util.List;

public interface IUnitConverter<T>{
    T convert(String fromUnit, String toUnit, T value);
    List<String> getConverterUnitListNames();
    Class<T> getValueType(); //Need this to specify to java which type as to be cast for CLI

}
