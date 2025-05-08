import "./Matrix.css";

import { useState, useEffect, useRef } from "react";
import Dropdown from "./Dropdown";
import Button from "./Button";
import PropTypes from "prop-types";

const Matrix = ({ setMatrixResult }) => {
    const [rows, setRows] = useState(2);
    const [cols, setCols] = useState(2);
    const [matrix, setMatrix] = useState([]);
    const [matrixA, setMatrixA] = useState([["", ""]]);
    const [operation, setOperation] = useState("");

    const matrixGridRef = useRef(null);

    const sizeOptions = [1, 2, 3, 4, 5].map(n => ({ value: n, label: n.toString() }));

    useEffect(() => {
        setMatrix(Array.from({ length: rows }, () =>
            Array.from({ length: cols }, () => "")
        ));
    }, [rows, cols]);

    const updateCell = (r, c, value) => {
        const newMatrix = matrix.map(row => [...row]);
        newMatrix[r][c] = value;
        setMatrix(newMatrix);
    };

    const calculateApiCall = async (matrixData, matrixB, scalar, operator) => {
        try {
            const response = await fetch("/api/matrix/calculate", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    matrix: matrixData,
                    matrixB: matrixB,
                    scalar: scalar,
                    operator: operator,
                }),
            });


            if (response.ok) {
                const result = await response.json();
                setMatrix(result);
                setMatrixResult(JSON.stringify(result));
            } else {
                const errorMessage = await response.text() || "Calculation error";
                alert(errorMessage);
            }
        } catch (error) {
            console.error("Parsing or network error :", error);
            alert("Unexpected error");
        }
    };

    const requestScalar = () => {
        const scalarValue = prompt("Enter a scalar:");
        if (scalarValue && !isNaN(Number(scalarValue))) {
            return parseFloat(scalarValue);
        } else {
            alert("Invalid value for scalar");
            return null;
        }
    };

    const emptyMatrix = matrix.map(row => row.map(() => ""));


    const handleBasicOperation = (operation) => {
        setMatrixA(matrix);
        setMatrix(emptyMatrix);
        setOperation(operation);
        setMatrixResult("Enter a second matrix");
    }

    const handleScalarOperation = async (operation) => {
        const scalarValue = requestScalar();
        if (scalarValue !== null) {
            await calculateApiCall(matrix, [], scalarValue.toString(), operation);
        }
    };

    const handleClear = () =>{
        setMatrix(Array.from({ length: rows }, () =>
            Array.from({ length: cols }, () => "")
        ));
        setMatrixResult("");
    };

    const handleCalculate = async () => {
        if (!matrixA || !operation) {
            alert("Invalid operation or missing matrix.");
            return;
        }

        await calculateApiCall(matrixA, matrix, "", operation);
        setMatrixA(null);
        setOperation("");
    };

    useEffect(() => {
        if (matrixGridRef.current) {
            const cols = matrix[0]?.length || 2;
            matrixGridRef.current.style.setProperty("--cols", cols.toString());
        }
    }, [matrix]);

    return (
        <div className="matrix-box">
            <div className="dropdowns">
                <Dropdown
                    title="Rows"
                    options={sizeOptions}
                    selectedOption={rows.toString()}
                    onChange={(value) => setRows(Number(value))}
                />
                <Dropdown
                    title="Columns"
                    options={sizeOptions}
                    selectedOption={cols.toString()}
                    onChange={(value) => setCols(Number(value))}
                />
            </div>

            <div className="matrix-grid" ref={matrixGridRef}>
                {matrix.map((row, rIdx) =>
                    row.map((val, cIdx) => (
                        <input
                            key={`${rIdx}-${cIdx}`}
                            value={val}
                            onChange={(e) => updateCell(rIdx, cIdx, e.target.value)}
                        />
                    ))
                )}
            </div>

            <div className="matrix-buttons">
                <div className="button-row row-4">
                    <Button value="Add" onClick={() => handleBasicOperation("add")} />
                    <Button value="Substract" onClick={() => handleBasicOperation("subtract")} />
                    <Button value="Multiply" onClick={() => handleBasicOperation("multiply")} />
                    <Button value="Divide" onClick={() => handleScalarOperation("divide")} />
                </div>
                <div className="button-row row-3">
                    <Button value="Scalar Multiply" onClick={() => handleScalarOperation("scalar")} />
                    <Button value="Transpose" onClick={() => calculateApiCall(matrix, [], "", "transpose")} />
                    <Button value="Inverse" onClick={() => calculateApiCall(matrix, [], "", "inverse")} />
                </div>
                <div className="button-row row-3">
                    <Button value="Identity" onClick={() => calculateApiCall(matrix, [], "", "identity")} />
                    <Button value="Clear" onClick={handleClear} />
                    <Button value="Calculate" onClick={handleCalculate} />
                </div>
            </div>
        </div>);
};

Matrix.propTypes = {
    setMatrixResult: PropTypes.func.isRequired
};

export default Matrix;