import { useState } from "react";
import Field from "./Field";
import Button from "./Button";
import Modal from "./Modal";

import "./ExpressionParser.css"
import PropTypes from "prop-types";

const ExpressionParser = ({ setExpressionParserResult }) => {
    const [expression, setExpression] = useState("");
    const [isModalOpen, setIsModalOpen] = useState(false);

    const handleParse = async () => {
        try {
            const response = await fetch("/api/parser/evaluate", {
                method: "POST",
                headers: {
                    "Content-Type": "text/plain"
                },
                body: expression
            });

            if (!response.ok) {
                const errorText = await response.text();
                alert("Error: " + errorText);
                return;
            }

            const result = await response.json();
            console.log("Result:", result);
            setExpressionParserResult(JSON.stringify(result));
        } catch (error) {
            console.error("Request failed:", error);
            alert("Unexpected error while parsing.");
        }
    };

    return (
        <div className="expression-parser">

            <Button value="ℹ️ Infos" onClick={() => setIsModalOpen(true)} />

            <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="ℹ️ Usage Guidelines">
                <br />
                <ul>
                    <li><strong>Supported types:</strong></li>
                    <ul>
                        <li>Integer</li>
                        <li>Real</li>
                        <li>Rational</li>
                        <li>Complex</li>
                        <li>Matrix</li>
                        <li>Linear equation</li>
                        <li>You can mix the first three types</li>
                    </ul>
                    <br />
                    <li><strong>Multiple notations supported:</strong></li>
                    <ul>
                        <li>Standard notation (example: (1+2+5)/5.5 )</li>
                        <li>Prefix notation (example: /(+(1,2,5),5.5) )</li>
                        <li>Postfix notation (example: ((1,2,5,3)+,5.5)/ )</li>
                    </ul>
                    <br />
                    <li><strong>Matrix:</strong></li>
                    <ul>
                        <li>Syntax: [[],[]] (example: [[1,2,3], [4,5,6]] + [[1,2,3], [4,5,6]]) </li>
                        <li>Transpose: ^T (example: ([[1,2,3], [4,5,6]])^T )</li>
                        <li>Identity: ^I (example: ([[1,2],[4,5]])^I )</li>
                        <li>Inverse: ^-1 (example: ([[1,2,3],[4,5,6],[7,8,50]])^-1 )</li>
                        <li>Prefix notation for the last three: T^ or I^ or -1^ (example: T^([[1,2,3],[4,5,6]]) )</li>
                    </ul>
                    <br />
                    <li><strong>Linear equation</strong></li>
                    <ul>
                        <li>Syntax: solve() (example: solve((x + y) = 5, (x - y) = 1) )</li>
                        <li>Prefix notation: solve(+(x,y) = 5, -(x,y) = 1)</li>
                        <li>Postfix notation: solve((x,y)+ = 5, (x,y)- = 1)</li>
                    </ul>
                </ul>
            </Modal>

            <Field
                title="Expression"
                value={expression}
                onChange={setExpression}
            />

            <Button value="Parse" onClick={handleParse} />
        </div>
    );
};

ExpressionParser.propTypes = {
    setExpressionParserResult: PropTypes.func.isRequired
};

export default ExpressionParser;
