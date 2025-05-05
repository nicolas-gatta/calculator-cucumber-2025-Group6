package calculator.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

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

    static Stream<Arguments> operationProvider() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of("add", "[[6.0,8.0],[10.0,12.0]]"),
                org.junit.jupiter.params.provider.Arguments.of("subtract", "[[-4.0,-4.0],[-4.0,-4.0]]"),
                org.junit.jupiter.params.provider.Arguments.of("multiply", "[[19.0,22.0],[43.0,50.0]]")
        );
    }

    @ParameterizedTest
    @MethodSource("operationProvider")
    void testMatrixOperations(String operator, String expected) throws Exception {
        MatrixRequest request = new MatrixRequest();
        request.setMatrix(MATRIX_A);
        request.setMatrixB(MATRIX_B);
        request.setOperator(operator);

        mockMvc.perform(post("/api/matrix/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }


}
