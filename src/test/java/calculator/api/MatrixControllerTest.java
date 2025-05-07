package calculator.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsString;
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
    private static final String SCALAR = "2.0";

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

    static Stream<Arguments> operationProviderWithScalar() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of("scalar", "[[2.0,4.0],[6.0,8.0]]"),
                org.junit.jupiter.params.provider.Arguments.of("divide", "[[0.5,1.0],[1.5,2.0]]")
                );
    }

    @ParameterizedTest
    @MethodSource("operationProviderWithScalar")
    void testMatrixScalarOperations(String operator, String expected) throws Exception {
        MatrixRequest request = new MatrixRequest();
        request.setMatrix(MATRIX_A);
        request.setScalar(SCALAR);
        request.setOperator(operator);

        mockMvc.perform(post("/api/matrix/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }


    static Stream<Arguments> operationProviderOneMatrix() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of("transpose", "[[1.0,3.0],[2.0,4.0]]"),
                org.junit.jupiter.params.provider.Arguments.of("inverse", "[[-2.0,1.0],[1.5,-0.5]]"),
                org.junit.jupiter.params.provider.Arguments.of("identity", "[[1.0,0.0],[0.0,1.0]]")
        );
    }

    @ParameterizedTest
    @MethodSource("operationProviderOneMatrix")
    void testOneMatrixOperations(String operator, String expected) throws Exception {
        MatrixRequest request = new MatrixRequest();
        request.setMatrix(MATRIX_A);
        request.setOperator(operator);

        mockMvc.perform(post("/api/matrix/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    void testBadOperator() throws Exception {
        MatrixRequest request = new MatrixRequest();
        request.setMatrix(MATRIX_A);
        request.setOperator("determinant");

        mockMvc.perform(post("/api/matrix/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: Invalid operator: determinant"));
    }

    @Test
    void testIllegalConstruction() throws Exception {
        MatrixRequest request = new MatrixRequest();
        request.setMatrix(null);
        request.setMatrixB(null);
        request.setOperator("add");

        mockMvc.perform(post("/api/matrix/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Error: Illegal construction:")));
    }

    static Stream<Arguments> operationNullMatrixProvider() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of("add"),
                org.junit.jupiter.params.provider.Arguments.of("subtract"),
                org.junit.jupiter.params.provider.Arguments.of("multiply")
        );
    }

    @ParameterizedTest
    @MethodSource("operationNullMatrixProvider")
    void testNullMatrixOperations(String operator) throws Exception {
        MatrixRequest request = new MatrixRequest();
        request.setMatrix(new double[][]{});
        request.setMatrixB(new double[][]{{}});
        request.setOperator(operator);

        mockMvc.perform(post("/api/matrix/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Error: Illegal construction:")));
    }


    static Stream<Arguments> operationProviderWithScalarNull() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of("scalar", ""),
                org.junit.jupiter.params.provider.Arguments.of("divide", null)
                );
    }

    @ParameterizedTest
    @MethodSource("operationProviderWithScalarNull")
    void testMatrixScalarNullOperations(String operator, String scalar) throws Exception {
        MatrixRequest request = new MatrixRequest();
        request.setMatrix(null);
        request.setScalar(scalar);
        request.setOperator(operator);

        mockMvc.perform(post("/api/matrix/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Error: Illegal construction:")));
    }


    static Stream<Arguments> operationProviderOneMatrixNull() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of("transpose"),
                org.junit.jupiter.params.provider.Arguments.of("inverse"),
                org.junit.jupiter.params.provider.Arguments.of("identity")
        );
    }

    @ParameterizedTest
    @MethodSource("operationProviderOneMatrixNull")
    void testOneMatrixNullOperations(String operator) throws Exception {
        MatrixRequest request = new MatrixRequest();
        request.setMatrix(null);
        request.setOperator(operator);

        mockMvc.perform(post("/api/matrix/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Error: Illegal construction:")));
    }


}
