import { useState } from "react";

import Screen from "./Screen";
import Wrapper from "./Wrapper";
import ButtonBox from "./ButtonBox";
import Button from "./Button";
import Dropdown from "./Dropdown";
import Head from "./Head";
import TypeBar from "./TypeBar";
import HelpModal from "./HelpModal";

import "./Calculator.css"

const btnHead = [{value: "+/-", className: "otherBtn"}, {value: "?", className: "helpBtn"}]

const btnValues = [
    [{value: "7", className: ""}, {value: "8", className: ""}, {value: "9", className: ""}, {value:"+", className: "operation"}],
    [{value: "4", className: ""}, {value: "5", className: ""}, {value: "6", className: ""}, {value:"-", className: "operation"}],
    [{value: "1", className: ""}, {value: "2", className: ""}, {value: "3", className: ""}, {value:"*", className: "operation"}],
    [{value: "C", className: "cBtn"}, {value: "0", className: ""}, {value: "=", className: "equals"}, {value:"/", className: "operation"}],
];

const typesNumber = [
    {value: "INTEGER", label: "Integer"},
    {value: "RATIONAL", label: "Rational"},
    {value: "REAL", label: "Real"},
    {value: "COMPLEX", label: "Complex"},
];

const specialButtonsByType = {
    REAL: [{ value: ".", className: "specialBtn" }],
    RATIONAL: [{ value: "/", className: "specialBtn" }],
    COMPLEX: [{ value: ".", className: "specialBtn" }, { value: "i", className: "specialBtn" }],
};

const Calculator = () => {
    const [selectedType, setSelectedType] = useState("INTEGER"); // ← stocke le type sélectionné
    const [isHelpOpen, setIsHelpOpen] = useState(false);
    const [firstOperand, setFirstOperand] = useState("");
    const [currentInput, setCurrentInput] = useState("");
    const [operator, setOperator] = useState("");
    const [isResultDisplayed, setIsResultDisplayed] = useState(false);

    const openHelpModal = () => setIsHelpOpen(true);
    const closeHelpModal = () => setIsHelpOpen(false);

    const updateInput = (value) => {
        if (isResultDisplayed) {
            setCurrentInput(value);
            setIsResultDisplayed(false);
        } else {
            setCurrentInput((prev) => prev + value);
        }
    };

    const handleSpecialButtonClick = (value) => {
        if (value === "/" && selectedType === "RATIONAL" && !currentInput.includes("/") && currentInput !== "") {
            setCurrentInput((prev) => prev + "/");
        } else if (value === "." && !currentInput.includes(".") && (selectedType === "REAL" || selectedType === "COMPLEX")) {
            setCurrentInput((prev) => prev + (prev === "" ? "0." : "."));
        } else if (value === "i" && selectedType === "COMPLEX" && !currentInput.includes("i")) {
            setCurrentInput((prev) => prev + "i");
        }
    };


    const handleButtonClick = (value) => {
        if (!isNaN(value)) {
            updateInput(value);
        }else{
            switch (value) {
                case "=":
                    setIsResultDisplayed(true);
                    calculate();
                    break;
                case "C":
                    clear();
                    break;
                case "+": case "-": case "*": case "/":
                    if (firstOperand && currentInput) {
                        calculate().then(() => {
                            setOperator(value);
                        });
                    }
                    setFirstOperand(currentInput);
                    setOperator(value);
                    setCurrentInput("");
                    break;
                default:
                    updateInput(value);
            }
        }


    };

    const calculate = async () => {
        if (firstOperand && operator && currentInput) {
            try {
                const response = await fetch("/api/calculate", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({
                        firstOperand: firstOperand,
                        operator: operator,
                        secondOperand: currentInput,
                        numberType: selectedType,
                    }),
                });

                const result = await response.text();
                setFirstOperand(result); // Pour un calcul en chaîne
                setCurrentInput("");
                setOperator("");
            } catch (error) {
                console.error("Erreur lors de l’appel API :", error);
            }
        }
    };

    const clear = () => {
        setFirstOperand("");
        setCurrentInput("");
        setOperator("");
        setIsResultDisplayed(false);
    };

    const getSpecialButtons = () => specialButtonsByType[selectedType] || [];


    return <Wrapper>
        <Head><Screen value={currentInput || firstOperand || "0"} />
            {btnHead.map((btn) => (
                <Button
                    key={btn.value}
                    className={btn.className}
                    value={btn.value}
                    onClick={() => btn.value === "?" ? openHelpModal() : null}
                />
            ))}
        </Head>
        <TypeBar> <Dropdown title={"Type"} options={typesNumber} selectedOption={selectedType}
                            onChange={(value) => setSelectedType(value)}
                            disabled={firstOperand !== "" || operator !== "" || currentInput !== ""}/>
            {getSpecialButtons().map((btn) => (
                <Button
                    key={btn.value}
                    className={btn.className}
                    value={btn.value}
                    onClick={() => handleSpecialButtonClick(btn.value)}
                />
            ))}
        </TypeBar>

        <ButtonBox>
            {btnValues.flat().map((btn) => (
                <Button
                    key={btn.value}
                    className={btn.className}
                    value={btn.value}
                    onClick={() => handleButtonClick(btn.value)}
                />
            ))}
        </ButtonBox>

        <HelpModal isOpen={isHelpOpen} onClose={closeHelpModal} ></HelpModal>
    </Wrapper>;
};

export default Calculator;