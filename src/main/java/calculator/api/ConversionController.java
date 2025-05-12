package calculator.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unit_converter.*;

import java.util.Locale;


/**
 * REST controller that handles unit conversion requests.
 * <p>
 * This controller provides an API endpoint to convert values between different units
 * (e.g., meters to kilometers, Celsius to Fahrenheit) based on the requested conversion type.
 * It uses the {@link ConverterFactory} to retrieve the correct converter implementation
 * depending on the conversion type provided in the request.
 * </p>
 */
@RestController
@RequestMapping("/api/conversion")
public class ConversionController {

    /**
     * Default constructor for ConversionController.
     */
    public ConversionController() {
        // Default constructor
    }

    /**
     * Endpoint for converting a numeric value from one unit to another.
     * <p>
     * Accepts a {@link ConversionRequest} JSON body containing:
     * <ul>
     *     <li>conversionType (e.g., "Length", "Temperature")</li>
     *     <li>fromUnit (e.g., "m")</li>
     *     <li>toUnit (e.g., "km")</li>
     *     <li>value (numeric string to be converted)</li>
     * </ul>
     * Returns a string representation of the converted value, formatted appropriately.
     *
     *
     * @param request the conversion request
     * @return the result of the unit conversion as a string
     * @throws IllegalArgumentException if the number format is invalid or if the converter type is unsupported
     */
    @PostMapping(value ="/convert", produces = "text/plain")
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

    /**
     * Formats the result of the conversion.
     * <p>
     * Uses scientific notation if the absolute value is very small (&lt; 0.0001)
     * or very large (&gt; 1000). Otherwise, it uses fixed-point notation with 6 decimal places,
     * and removes unnecessary trailing zeros.
     * </p>
     *
     * @param result the numerical result of the conversion
     * @return the formatted string
     */
    private String formatResult(double result) {
        if(Math.abs(result) < 0.0001 || Math.abs(result) > 1000) {
            return String.format(Locale.US, "%.6e", result);
        } else {
            return String.format(Locale.US, "%.6f", result).replaceAll("^(\\d+\\.\\d*?[0-9])0+$", "$1");
        }
    }
}
