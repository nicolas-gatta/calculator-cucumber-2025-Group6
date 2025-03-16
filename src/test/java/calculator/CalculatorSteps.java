package calculator;

import static org.junit.jupiter.api.Assertions.*;

import calculator.numbers.MyNumber;
import calculator.operations.*;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

public class CalculatorSteps {

//	static final Logger log = getLogger(lookup().lookupClass());

	private ArrayList<Expression> params;
	private Operation op;
	private Calculator c;
	private String currentOperation;

	@Before
    public void resetMemoryBeforeEachScenario() {
		params = new ArrayList<>();
		op = null;
		currentOperation = null;
	}

	@Given("I initialise a calculator")
	public void givenIInitialiseACalculator() {
		c = new Calculator();
	}

	@Given("an integer operation {string}")
	public void givenAnIntegerOperation(String s) {
		currentOperation = s;
		params = new ArrayList<>();
	}

	// The following example shows how to use a DataTable provided as input.
	// The example looks slightly complex, since DataTables can take as input
	//  tables in two dimensions, i.e. rows and lines. This is why the input
	//  is a list of lists.
	@Given("the following list of integer numbers")
	public void givenTheFollowingListOfNumbers(List<List<String>> numbers) {
		params = new ArrayList<>();
		// Since we only use one line of input, we use get(0) to take the first line of the list,
		// which is a list of strings, that we will manually convert to integers:
		numbers.get(0).forEach(n -> params.add(new MyNumber(Integer.parseInt(n))));
	    params.forEach(n -> System.out.println("value ="+ n));
		
		createOperation();
	}

	// The string in the Given annotation shows how to use regular expressions...
	// In this example, the notation d+ is used to represent numbers, i.e. nonempty sequences of digits
	@Given("^the sum of two numbers (\\d+) and (\\d+)$")
	// The alternative, and in this case simpler, notation would be:
	// @Given("the sum of two numbers {int} and {int}")
	public void givenTheSum(int n1, int n2) {
		try {
			params = new ArrayList<>();
		    params.add(new MyNumber(n1));
		    params.add(new MyNumber(n2));
		    op = new Plus(params);}
		catch(IllegalConstruction e) { fail("Failed to create Plus operation: " + e.getMessage()); }
	}

	@Then("^its (.*) notation is (.*)$")
	public void thenItsNotationIs(String notation, String s) {
		if (notation.equals("PREFIX")||notation.equals("POSTFIX")||notation.equals("INFIX")) {
			op.notation = Notation.valueOf(notation);
			assertEquals(s, op.toString());
		}
		else fail(notation + " is not a correct notation! ");
	}

	@When("^I provide a (.*) number (\\d+)$")
	public void whenIProvideANumber(String s, int val) {
		params.add(new MyNumber(val));
		
		if (currentOperation != null && params.size() == 1) {
		} 
		else if (currentOperation != null && params.size() >= 2) {
			createOperation();
		}
		else if (op != null) {
			ArrayList<Expression> newParams = new ArrayList<>();
			newParams.add(new MyNumber(val));
			op.addMoreParams(newParams);
		}
	}

	private void createOperation() {
		if (currentOperation == null || params.isEmpty()) {
			return;
		}
		
		try {
			switch (currentOperation) {
				case "+"	->	op = new Plus(params);
				case "-"	->	op = new Minus(params);
				case "*"	->	op = new Times(params);
				case "/"	->	op = new Divides(params);
				default		->	fail("Unknown operation: " + currentOperation);
			}
		} catch (IllegalConstruction e) {
			fail("Failed to create operation: " + e.getMessage());
		}
	}

	@Then("^the (.*) is (\\d+)$")
	public void thenTheOperationIs(String s, int val) {
		try {
			if (params == null || params.isEmpty()) {
				fail("No parameters provided for operation");
			}
			
			switch (s) {
				case "sum"			->	op = new Plus(params);
				case "product"		->	op = new Times(params);
				case "quotient"		->	op = new Divides(params);
				case "difference"	->	op = new Minus(params);
				default -> fail("Unknown operation type: " + s);
			}
			MyNumber result = (MyNumber) c.eval(op);
            assertEquals(val, result.getValue());
		} catch (IllegalConstruction e) {
			fail("Failed to create operation: " + e.getMessage());
		}
	}

	@Then("the operation evaluates to {int}")
	public void thenTheOperationEvaluatesTo(int val) {
		MyNumber result = (MyNumber) c.eval(op);
		assertEquals(val, result.getValue());
	}

	@Then("the operation evaluates to null")
	public void thenTheOperationEvaluatesTo() {assertNull(c.eval(op));}

}
