package calculator.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ConversionController.class)
class ConversionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testValidLengthConversion() throws Exception {
        ConversionRequest request = new ConversionRequest();
        request.setConversionType("LENGTH");
        request.setFromUnit("METER");
        request.setToUnit("CENTIMETER");
        request.setValue("1");

        mockMvc.perform(post("/api/conversion/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("100.0")); // assuming 1 m = 100 cm
    }

    @Test
    void testInvalidNumberFormat() throws Exception {
        ConversionRequest request = new ConversionRequest();
        request.setConversionType("LENGTH");
        request.setFromUnit("METER");
        request.setToUnit("CENTIMETER");
        request.setValue("abc"); // invalid input

        mockMvc.perform(post("/api/conversion/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUnsupportedConverter() throws Exception {
        ConversionRequest request = new ConversionRequest();
        request.setConversionType("UNKNOWN_TYPE");
        request.setFromUnit("X");
        request.setToUnit("Y");
        request.setValue("10");

        mockMvc.perform(post("/api/conversion/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSmallNumberFormatting() throws Exception {
        ConversionRequest request = new ConversionRequest();
        request.setConversionType("LENGTH");
        request.setFromUnit("MILLIMETER");
        request.setToUnit("METER");
        request.setValue("0.00001");

        mockMvc.perform(post("/api/conversion/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("1.000000e-08"));
    }

    @Test
    void testBigNumberFormatting() throws Exception {
        ConversionRequest request = new ConversionRequest();
        request.setConversionType("LENGTH");
        request.setToUnit("MILLIMETER");
        request.setFromUnit("METER");
        request.setValue("100000");

        mockMvc.perform(post("/api/conversion/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("1.000000e+08"));
    }
}
