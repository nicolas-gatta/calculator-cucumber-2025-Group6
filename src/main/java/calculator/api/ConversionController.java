package calculator.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unit_converter.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/conversion")
public class ConversionController {

    @PostMapping("/convert")
    public String convert(@RequestBody ConversionRequest request) {
        try{
            String conversionType = request.getConversionType();
            String fromUnit = request.getFromUnit();
            String toUnit = request.getToUnit();
            String value = request.getValue();

            IUnitConverter<?> converter = ConverterFactory.getConverter(conversionType);

            if(converter != null) {
                double numericValue = Double.parseDouble(value);
                @SuppressWarnings("unchecked")
                double result = ((IUnitConverter<Double>) converter).convert(fromUnit, toUnit, numericValue);
                return formatResult(result);
            }else{
                throw new IllegalArgumentException("Unsupported converter type implementation");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error : Invalid number format: " + request.getValue());
        }
    }

    private String formatResult(double result) {
        if(Math.abs(result) < 0.0001 || Math.abs(result) > 1000) {
            return String.format(Locale.US, "%.6e", result);
        } else {
            return String.format(Locale.US, "%.6f", result).replaceAll("0+$", "0");
        }
    }
}
