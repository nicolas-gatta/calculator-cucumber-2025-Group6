package calculator.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(LinearEquationController.class)
class LinearEquationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testLinearEquation() throws Exception {
        LinearEquationRequest request = new LinearEquationRequest();
        request.setCoefficients(new double[][] {{3,3},{3,-4}});
        request.setConstants(new double[][] {{5},{7}});
        List<String> variables = new ArrayList<>();
        variables.add("x");
        variables.add("y");
        request.setVariables(variables);

        String expected = "(x = 1.9524, y = -0.2857)";

        mockMvc.perform(post("/api/linearEquation/solve")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    void testLinearEquationWithException() throws Exception {
        LinearEquationRequest request = new LinearEquationRequest();
        request.setCoefficients(new double[][] {{3,3},{3,-4}});
        request.setConstants(new double[][] {{5}});
        List<String> variables = new ArrayList<>();
        variables.add("x");
        variables.add("y");
        request.setVariables(variables);

        mockMvc.perform(post("/api/linearEquation/solve")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Error while solving the system: ")));
    }
}
