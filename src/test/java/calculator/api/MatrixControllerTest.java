package calculator.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(MatrixController.class)
class MatrixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final double[][] MATRIX_A = {{1, 2}, {3, 4}};
    private static final double[][] MATRIX_B = {{5, 6}, {7, 8}};

    @Test
    void testAdd() throws Exception {
        MatrixRequest request = new MatrixRequest();
        request.setMatrix(MATRIX_A);
        request.setMatrixB(MATRIX_B);
        request.setOperator("add");

        String expected = "[[6.0,8.0],[10.0,12.0]]";

        mockMvc.perform(post("/api/matrix/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    void testSubtract() throws Exception {
        MatrixRequest request = new MatrixRequest();
        request.setMatrix(MATRIX_A);
        request.setMatrixB(MATRIX_B);
        request.setOperator("subtract");

        String expected = "[[-4.0,-4.0],[-4.0,-4.0]]";

        mockMvc.perform(post("/api/matrix/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }


}
