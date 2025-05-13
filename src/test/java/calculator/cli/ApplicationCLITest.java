package calculator.cli;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationCLITest {

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void teardown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        outputStreamCaptor.reset();
    }

    @Test
    @Order(2)
    void testHandleCommandReturnsTrueOnExit() {
        assertTrue(ApplicationCLI.handleCommand("exit", "some help"));
    }

    @Test
    @Order(3)
    void testHandleCommandDisplaysHelp() {
        assertFalse(ApplicationCLI.handleCommand("help", "Expected help message here"));
    }

    @Test
    @Order(4)
    void testHandleCommandReturnsFalseOnEmptyInput() {
        assertFalse(ApplicationCLI.handleCommand("", "help"));
    }

    @Test
    @Order(5)
    void testHandleCommandReturnsFalseOnInputWithSpaces() {
        assertFalse(ApplicationCLI.handleCommand(" ", "help"));
    }


    @Test
    @Order(6)
    void testHandleCommandReturnsFalseOnWrongInput() {
        assertFalse(ApplicationCLI.handleCommand("blablabla", "help"));
    }


    @Test
    @Order(7)
    void testPrivateConstructorThrowsException() {
        assertThrows(IllegalStateException.class, ApplicationCLI::new);
    }

    @Test
    @Order(1)
    void testEntryPoint(){

        String simulatedUserInput = "help\nblabla\ncalculator\nhelp\n[[]]\n5+8\nexit\nconverter\nhelp\nblabla\nLength\ncentimeter\nmeter\n80\nexit\nexit\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());

        System.setIn(inputStream);

        ApplicationCLI.entryPointCli();

        String consoleOutput = outputStreamCaptor.toString();

        assertTrue(consoleOutput.contains(ApplicationCLI.TOOL_CHOOSER_MESSAGE));
        assertTrue(consoleOutput.contains("Choose your tool"));
        assertTrue(consoleOutput.contains("> "));
        assertTrue(consoleOutput.contains(ApplicationCLI.MAIN_HELP_MESSAGE));
        assertTrue(consoleOutput.contains("Unknown command. Type 'help' or 'exit'."));
        assertTrue(consoleOutput.contains(ApplicationCLI.CALCULATOR_MESSAGE));
        assertTrue(consoleOutput.contains(ApplicationCLI.CALCULATOR_HELP_MESSAGE));
        assertTrue(consoleOutput.contains("Enter your expression"));
        assertTrue(consoleOutput.contains("> "));
        assertTrue(consoleOutput.contains("Error: Invalid expression."));
        assertTrue(consoleOutput.contains("The result of evaluating expression (5 + 8)"));
        assertTrue(consoleOutput.contains("is: 13."));
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
        assertTrue(consoleOutput.contains("Result: 80,0000 centimeter = 0,8000 meter") || consoleOutput.contains("Result: 80.0000 centimeter = 0.8000 meter"));
        assertTrue(consoleOutput.contains("Exit Application"));
    }
}
