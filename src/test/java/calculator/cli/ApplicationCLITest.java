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

    @AfterEach
    public void restoreSystemOut() {
        System.setOut(standardOut);
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
    void testHandleCommandReturnsFalseOnInputWithSpaces() {
        assertFalse(ApplicationCLI.handleCommand(" ", "help"));
    }


    @Test
    void testHandleCommandReturnsFalseOnWrongInput() {
        assertFalse(ApplicationCLI.handleCommand("blablabla", "help"));
    }


    @Test
    void testPrivateConstructorThrowsException() {
        assertThrows(IllegalStateException.class, ApplicationCLI::new);
    }

    @Test
    void testEntryPointHelp(){

        String simulatedUserInput = "help\nexit\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());

        System.setIn(inputStream);

        ApplicationCLI.entryPointCli();

        System.setIn(System.in);

        String consoleOutput = outputStreamCaptor.toString();

        System.out.println(consoleOutput);

        assertTrue(consoleOutput.contains(ApplicationCLI.TOOL_CHOOSER_MESSAGE));
        assertTrue(consoleOutput.contains("Choose your tool"));
        assertTrue(consoleOutput.contains("> "));
        assertTrue(consoleOutput.contains(ApplicationCLI.MAIN_HELP_MESSAGE));
        assertTrue(consoleOutput.contains("Exit Application"));
    }

    @Test
    void testEntryPointInvalid(){

        String simulatedUserInput = "blabla\nexit\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());

        System.setIn(inputStream);

        ApplicationCLI.entryPointCli();

        System.setIn(System.in);

        String consoleOutput = outputStreamCaptor.toString();

        assertTrue(consoleOutput.contains(ApplicationCLI.TOOL_CHOOSER_MESSAGE));
        assertTrue(consoleOutput.contains("Choose your tool"));
        assertTrue(consoleOutput.contains("> "));
        assertTrue(consoleOutput.contains("Unknown command. Type 'help' or 'exit'."));
        assertTrue(consoleOutput.contains("Exit Application"));
    }

    @Test
    void testEntryPointCalculator(){

        String simulatedUserInput = "calculator\nhelp\n[[]]\n5+8\nexit\nexit\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());

        System.setIn(inputStream);

        ApplicationCLI.entryPointCli();

        System.setIn(System.in);

        String consoleOutput = outputStreamCaptor.toString();

        assertTrue(consoleOutput.contains(ApplicationCLI.TOOL_CHOOSER_MESSAGE));
        assertTrue(consoleOutput.contains("Choose your tool"));
        assertTrue(consoleOutput.contains("> "));
        assertTrue(consoleOutput.contains(ApplicationCLI.CALCULATOR_MESSAGE));
        assertTrue(consoleOutput.contains(ApplicationCLI.CALCULATOR_HELP_MESSAGE));
        assertTrue(consoleOutput.contains("Enter your expression"));
        assertTrue(consoleOutput.contains("> "));
        assertTrue(consoleOutput.contains("Error: Invalid expression."));
        assertTrue(consoleOutput.contains("The result of evaluating expression (5 + 8)"));
        assertTrue(consoleOutput.contains("is: 13."));
        assertTrue(consoleOutput.contains("Exit Application"));

    }

    @Test
    void testEntryPointConverter(){

        String simulatedUserInput = "converter\nhelp\nblabla\nLength\ncentimeter\nmeter\n80\nexit\nexit\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());

        System.setIn(inputStream);

        ApplicationCLI.entryPointCli();

        System.setIn(System.in);

        String consoleOutput = outputStreamCaptor.toString();

        assertTrue(consoleOutput.contains(ApplicationCLI.TOOL_CHOOSER_MESSAGE));
        assertTrue(consoleOutput.contains("Choose your tool"));
        assertTrue(consoleOutput.contains("> "));
        assertTrue(consoleOutput.contains(ApplicationCLI.CONVERTER_MESSAGE));
        assertTrue(consoleOutput.contains("Enter conversion type"));
        assertTrue(consoleOutput.contains("> "));
        assertTrue(consoleOutput.contains("Available converters: Length, Volume, Temperature, Area, Density, Currency, Pressure, Speed, Energy, Force, Time, Binary"));
        assertTrue(consoleOutput.contains(ApplicationCLI.CONVERTER_HELP_MESSAGE));
        assertTrue(consoleOutput.contains("Unknown conversion type"));
        assertTrue(consoleOutput.contains("Available converter units: Millimeter, Centimeter, Decimeter, Meter, Kilometer, Inch, Foot, Yard, Mile, Nautical Mile, Light Year, Angstrom"));
        assertTrue(consoleOutput.contains("Enter source unit"));
        assertTrue(consoleOutput.contains("> "));
        assertTrue(consoleOutput.contains("Enter target unit"));
        assertTrue(consoleOutput.contains("> "));
        assertTrue(consoleOutput.contains("Enter value"));
        assertTrue(consoleOutput.contains("> "));
        assertTrue(consoleOutput.contains("Result: 80,0000 centimeter = 0,8000 meter"));
        assertTrue(consoleOutput.contains("Exit Application"));
    }
}
