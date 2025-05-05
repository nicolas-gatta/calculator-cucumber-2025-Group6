package calculator.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CalculatorController.class)
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testIntegerAddition() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("12");
        request.setSecondOperand("30");
        request.setOperator("+");
        request.setNumberType("INTEGER");

        mockMvc.perform(post("/api/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("42"));
    }

    @Test
    void testIntegerSubtraction() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("30");
        request.setSecondOperand("10");
        request.setOperator("-");
        request.setNumberType("INTEGER");

        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("20"));
    }

    @Test
    void testIntegerMultiplication() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("30");
        request.setSecondOperand("10");
        request.setOperator("*");
        request.setNumberType("INTEGER");

        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("300"));
    }

    @Test
    void testIntegerDivision() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("30");
        request.setSecondOperand("10");
        request.setOperator("/");
        request.setNumberType("INTEGER");

        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("3"));
    }

    @Test
    void testRealAddition() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("30");
        request.setSecondOperand("10");
        request.setOperator("+");
        request.setNumberType("REAL");

        mockMvc.perform(post("/api/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("40.000000"));
    }

    @Test
    void testRealSubtraction() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("30.5");
        request.setSecondOperand("10.4");
        request.setOperator("-");
        request.setNumberType("REAL");

        mockMvc.perform(post("/api/calculate")
                .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("20.100000"));
    }

    @Test
    void testRealMultiplication() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("30.5");
        request.setSecondOperand("10.4");
        request.setOperator("*");
        request.setNumberType("REAL");

        mockMvc.perform(post("/api/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("317.200000"));
    }

    @Test
    void testRealDivision() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("30.5");
        request.setSecondOperand("10.4");
        request.setOperator("/");
        request.setNumberType("REAL");

        mockMvc.perform(post("/api/calculate")
        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("2.932692"));
    }

    @Test
    void testRationalAddition() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("3/2");
        request.setSecondOperand("4/5");
        request.setOperator("+");
        request.setNumberType("RATIONAL");

        mockMvc.perform(post("/api/calculate")
        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("23/10"));
    }

    @Test
    void testRationalSubtraction() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("3/2");
        request.setSecondOperand("4/5");
        request.setOperator("-");
        request.setNumberType("RATIONAL");

        mockMvc.perform(post("/api/calculate")
        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("7/10"));
    }

    @Test
    void testRationalMultiplication() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("3/2");
        request.setSecondOperand("4/5");
        request.setOperator("*");
        request.setNumberType("RATIONAL");

        mockMvc.perform(post("/api/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("6/5"));
    }

    @Test
    void testRationalDivision() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("2");
        request.setSecondOperand("3");
        request.setOperator("/");
        request.setNumberType("RATIONAL");

        mockMvc.perform(post("/api/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("2/3"));
    }

    @Test
    void testComplexAddition() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("i");
        request.setSecondOperand("-i");
        request.setOperator("+");
        request.setNumberType("COMPLEX");

        mockMvc.perform(post("/api/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0 + 0.0i"));
    }

    @Test
    void testComplexSubtraction() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("3i");
        request.setSecondOperand("1");
        request.setOperator("-");
        request.setNumberType("COMPLEX");

        mockMvc.perform(post("/api/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("-1.0 + 3.0i"));
    }

    @Test
    void testComplexMultiplication() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("1+3i");
        request.setSecondOperand("i");
        request.setOperator("*");
        request.setNumberType("COMPLEX");

        mockMvc.perform(post("/api/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("-3.0 + 1.0i"));

    }

    @Test
    void testComplexDivision() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("i");
        request.setSecondOperand("1");
        request.setOperator("/");
        request.setNumberType("COMPLEX");

        mockMvc.perform(post("/api/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0 + 1.0i"));
    }

    @Test
    void testBadOperator() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("3");
        request.setSecondOperand("10");
        request.setOperator("0");
        request.setNumberType("INTEGER");

        mockMvc.perform(post("/api/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: Invalid operator: 0"));
    }

    @Test
    void testBadNumberType() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("3");
        request.setSecondOperand("10");
        request.setOperator("0");
        request.setNumberType("NULL");

        mockMvc.perform(post("/api/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: Invalid number type: NULL"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"INTEGER", "REAL", "RATIONAL", "COMPLEX"})
    void testIllegalConstructionWithVariousNumberTypes(String numberType) throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand(null);
        request.setSecondOperand(null);
        request.setOperator("+");
        request.setNumberType(numberType);

        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Error: Illegal construction:")));
    }

    @Test
    void testUnexpectedException() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setFirstOperand("3");
        request.setSecondOperand("0");
        request.setOperator("/");
        request.setNumberType("INTEGER");

        mockMvc.perform(post("/api/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Unexpected error:")));
    }
}
