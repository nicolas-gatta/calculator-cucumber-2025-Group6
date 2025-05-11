import { useState } from "react";
import Dropdown from "./Dropdown";
import Field from "./Field";
import Button from "./Button";
import "./LinearEquationSolver.css"
import PropTypes from "prop-types";

const LinearEquationSolver = ( { setLinearEquationResult }) => {
    const [numEquations, setNumEquations] = useState(2);
    const [numVariables, setNumVariables] = useState(2);
    const [variables, setVariables] = useState(["x", "y"]);
    const [coefficients, setCoefficients] = useState([[0, 0], [0, 0]]);
    const [constants, setConstants] = useState([[0], [0]]);
    const [result, setResult] = useState("");

    const handleVariableChange = (index, value) => {
        const newVars = [...variables];
        newVars[index] = value;
        setVariables(newVars);
    };

    const handleCoefficientChange = (row, col, value) => {
        const newCoefficients = coefficients.map(arr => [...arr]);
        newCoefficients[row][col] = parseFloat(value) || 0;
        setCoefficients(newCoefficients);
    };

    const handleConstantChange = (row, value) => {
        const newConstants = constants.map(arr => [...arr]);
        newConstants[row][0] = parseFloat(value) || 0;
        setConstants(newConstants);
    };

    const handleDimensionChange = (newEquations, newVars) => {
        setNumEquations(newEquations);
        setNumVariables(newVars);
        setVariables(Array.from({ length: newVars }, (_, i) => `x${i + 1}`));
        setCoefficients(Array.from({ length: newEquations }, () => Array(newVars).fill(0)));
        setConstants(Array.from({ length: newEquations }, () => [0]));
        setResult("");
    };

    const handleSolve = async () => {
        try {
            const response = await fetch("/api/linearEquation/solve", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ coefficients, constants, variables }),
            });

            if (!response.ok) {
                console.error("API Error");
                setResult("API Error");
                return;
            }

            const text = await response.text();
            setResult(text);
            setLinearEquationResult(text)
        } catch (e) {
            console.error(e);
            const error = "Error solving equation.";
            setResult(error);
            setLinearEquationResult(error)
        }
    };

    return (
        <div className="linear-equation-solver">
            <div className="dropdowns">
                <Dropdown
                    title="Number of Equations"
                    options={[1, 2, 3, 4, 5].map(v => ({ value: v, label: `${v}` }))}
                    selectedOption={numEquations.toString()}
                    onChange={(value) => handleDimensionChange(parseInt(value), numVariables)}
                />
                <Dropdown
                    title="Number of Variables"
                    options={[1, 2, 3, 4, 5].map(v => ({ value: v, label: `${v}` }))}
                    selectedOption={numVariables.toString()}
                    onChange={(value) => handleDimensionChange(numEquations, parseInt(value))}
                />
            </div>

            <h3>Variable Names : </h3>
            <br/>
            <div className="variables-inputs">
                {variables.map((v, i) => (
                    <Field
                        key={`Variable ${i + 1}`}
                        title={`Variable ${i + 1}`}
                        value={v}
                        onChange={(val) => handleVariableChange(i, val)}
                    />
                ))}
            </div>
            <br/>

            <div className="table-header">
                <h3>Coefficients and Constants :</h3>
                <Button value="Solve" onClick={handleSolve} />
            </div>
            {result && (
                <div className="result">
                    <h4>Result:</h4>
                    <pre>{result}</pre>
                </div>
            )}
            <table className="matrix-input">
                <thead>
                <tr>
                    {variables.map((v, i) => (
                        <th key={`input-${i}-${i}`}>{v}</th>
                    ))}
                    <th>Constant</th>
                </tr>
                </thead>
                <tbody>
                {coefficients.map((row, rowIndex) => (
                    <tr key={`row-${rowIndex}-${rowIndex}`}>
                        {row.map((val, colIndex) => (
                            <td key={`cell-${rowIndex}-${colIndex}`}>
                                <input
                                    type="number"
                                    value={val}
                                    onChange={(e) =>
                                        handleCoefficientChange(rowIndex, colIndex, e.target.value)
                                    }
                                />
                            </td>
                        ))}
                        <td key={`const-${rowIndex}-${rowIndex}`}>
                            <input
                                type="number"
                                value={constants[rowIndex][0]}
                                onChange={(e) =>
                                    handleConstantChange(rowIndex, e.target.value)
                                }
                            />
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>


        </div>
    );
};

LinearEquationSolver.propTypes = {
    setLinearEquationResult: PropTypes.func.isRequired
};

export default LinearEquationSolver;