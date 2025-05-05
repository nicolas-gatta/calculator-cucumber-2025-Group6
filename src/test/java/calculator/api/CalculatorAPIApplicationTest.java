package calculator.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculatorAPIApplicationTest {

    @Test
    void mainRunsWithoutException() {
        try {
            CalculatorAPIApplication.main(new String[]{});
            assertTrue(true);
        } catch (Exception e) {
            throw new AssertionError("Application failed to start", e);
        }
    }
}
