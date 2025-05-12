package visitor;

import calculator.IllegalConstruction;
import calculator.linear.Equation;
import calculator.linear.EquationExpression;
import calculator.linear.LinearEquationSystemExpression;
import calculator.linear.VariableExpression;
import calculator.matrix.Matrix;
import calculator.matrix.MatrixExpression;
import calculator.numbers.ComplexNumber;
import calculator.numbers.MyNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.operations.Minus;
import calculator.operations.Plus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EquationCollectorVisitorTest {

    private LinearEquationSystemExpression system;
    private LinearEquationSystemExpression complexSystem;
    private LinearEquationSystemExpression matrixSystem;
    private EquationCollectorVisitor visitor;

    @BeforeAll
    void setupOnce() throws IllegalConstruction {
        // System:
        // x -( -y + z) = 6
        // 2x + 3y = 0
        // 3x + 4y - 10z = -6

        EquationExpression eq1 = new EquationExpression(new Equation(
                new Minus(List.of(new VariableExpression("x"), new Plus(List.of(new VariableExpression(new MyNumber(-1),"y"), new VariableExpression(new MyNumber(-1),"z"))))),
                new MyNumber(6))
        );

        EquationExpression eq2 = new EquationExpression(new Equation(
                new Plus(List.of(new VariableExpression(new MyNumber(2), "x"), new VariableExpression(new MyNumber(3), "y"))),
                new MyNumber(0)));

        EquationExpression eq3 = new EquationExpression(new Equation(
                new Plus(List.of(new VariableExpression(new MyNumber(3), "x"),
                        new Minus(List.of(new VariableExpression(new RationalNumber(8,2), "y"), new VariableExpression(new MyNumber(10), "z"))))),
                new MyNumber(-6)
        ));

        EquationExpression eq4 = new EquationExpression(new Equation(new ComplexNumber(50,80),new RealNumber(20)));

        EquationExpression eq5 = new EquationExpression(new Equation(new MatrixExpression(new Matrix("[[1],[2]]")),new RealNumber(20)));

        visitor = new EquationCollectorVisitor();

        system = new LinearEquationSystemExpression(List.of(eq1, eq2, eq3));

        complexSystem = new LinearEquationSystemExpression(List.of(eq1, eq2, eq4));

        matrixSystem = new LinearEquationSystemExpression(List.of(eq1, eq2, eq5));

        visitor.visit(system);
    }

    @Test
    void testGatherAllVariables(){
        List<String> variables = visitor.getAllVariables();
        assertEquals(List.of("x", "y", "z"), variables);
    }

    @Test
    void testGatherMatrixValues() {

        // Vérifier la matrice des coefficients
        double[][] expectedMatrix = {{1.0, 1.0, 1.0}, {2.0, 3.0, 0.0}, {3.0, 4.0, -10.0}};

        System.out.println(system);

        assertArrayEquals(expectedMatrix, visitor.getEquationSystemAsMatrix().getValues());
    }

    @Test
    void testGatherConstantValues() {
        double[][] expectedConstants = {{6.0}, {0.0}, {-6.0}};

        assertArrayEquals(expectedConstants, visitor.getEquationEqualsValuesAsMatrix().getValues());
    }

    @Test
    void testInvalidMatrixSystem(){
        assertThrows(IllegalArgumentException.class, () -> new EquationCollectorVisitor().visit(matrixSystem));
    }

    @Test
    void testInvalidComplexSystem(){
        assertThrows(IllegalArgumentException.class, () -> new EquationCollectorVisitor().visit(complexSystem));
    }
}