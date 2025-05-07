package calculator.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unit_converter.IUnitConverter;
import unit_converter.NumberSystemConverter;

@RestController
@RequestMapping("/api/programmer")
public class ProgrammerConversionController {

    @PostMapping("/convert")
    public String convert(@RequestBody ProgrammerConversionRequest request) {
        String fromUnit = request.getFromUnit();
        String toUnit = request.getToUnit();
        String value = request.getValue();

        IUnitConverter<String> converter = new NumberSystemConverter();

        return converter.convert(fromUnit, toUnit, value);
    }
}
