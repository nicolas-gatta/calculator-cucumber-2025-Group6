package calculator.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProgrammerConversionController.class)
class ProgrammerConversionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testValidConversion() throws Exception {
        ProgrammerConversionRequest request = new ProgrammerConversionRequest();
        request.setFromUnit("DECIMAL");
        request.setToUnit("BINARY");
        request.setValue("10");

        mockMvc.perform(post("/api/programmer/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("0b1010")); // assuming 1 m = 100 cm
    }

    @Test
    void testNotValidConversion() throws Exception {
        ProgrammerConversionRequest request = new ProgrammerConversionRequest();
        request.setFromUnit("DECIMAL");
        request.setToUnit("BINARY");
        request.setValue("0b10");

        mockMvc.perform(post("/api/programmer/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Error: Invalid number format "))); // assuming 1 m = 100 cm
    }
}
