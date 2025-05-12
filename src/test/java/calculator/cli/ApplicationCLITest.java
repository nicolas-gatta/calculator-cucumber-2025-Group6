package calculator.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicationCLITest {
    @Test
    void testHandleCommandReturnsTrueOnExit() {
        assertTrue(ApplicationCLI.handleCommand("exit", "some help"));
    }

    @Test
    void testHandleCommandDisplaysHelp() {
        assertFalse(ApplicationCLI.handleCommand("help", "Expected help message here"));
    }

    @Test
    void testHandleCommandReturnsFalseOnEmptyInput() {
        assertFalse(ApplicationCLI.handleCommand("", "help"));
    }
}
