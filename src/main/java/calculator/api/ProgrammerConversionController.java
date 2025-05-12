package calculator.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unit_converter.IUnitConverter;
import unit_converter.NumberSystemConverter;

/**
 * REST controller for handling number system conversions commonly used in programming.
 * <p>
 * This controller supports conversions between binary, decimal, hexadecimal, and octal systems.
 * </p>
 */
@RestController
@RequestMapping("/api/programmer")
public class ProgrammerConversionController {

    /**
     * Default constructor for ProgrammerConversionController.
     */
    public ProgrammerConversionController() {
        // Default constructor
    }

    /**
     * Converts a number from one number system to another (e.g., from binary to hexadecimal).
     *
     * @param request the {@link ProgrammerConversionRequest} containing the input value,
     *                source base, and target base
     * @return the converted value as a string
     */
    @PostMapping("/convert")
    public String convert(@RequestBody ProgrammerConversionRequest request) {
        String fromUnit = request.getFromUnit();
        String toUnit = request.getToUnit();
        String value = request.getValue();

        IUnitConverter<String> converter = new NumberSystemConverter();

        return converter.convert(fromUnit, toUnit, value);
    }
}
