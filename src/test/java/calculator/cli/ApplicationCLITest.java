package calculator.cli;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unit_converter.enums.EnumDisplayUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class ApplicationCLITest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

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

    @Test
    void testPrivateConstructorThrowsException() {
        assertThrows(IllegalStateException.class, ApplicationCLI::new);
    }

    @Test
    void testEntryPoint(){

        String simulatedUserInput = "help\nexit\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputStream);

        ApplicationCLI.entryPointCli();

        System.setIn(System.in);
        System.setOut(standardOut);

        String consoleOutput = standardOut.toString();

        System.out.println(consoleOutput.toString());

        assertTrue(consoleOutput.contains(ApplicationCLI.TOOL_CHOOSER_MESSAGE));
        assertTrue(consoleOutput.contains(ApplicationCLI.MAIN_HELP_MESSAGE));
        assertTrue(consoleOutput.contains("Exit Application"));
    }
}
