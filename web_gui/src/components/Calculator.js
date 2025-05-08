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
import Matrix from "./Matrix";
import Converter from "./Converter";

const Calculator = () => {
    const [selectedType, setSelectedType] = useState("INTEGER");
    const [isHelpOpen, setIsHelpOpen] = useState(false);
    const [firstOperand, setFirstOperand] = useState("");
    const [currentInput, setCurrentInput] = useState("");
    const [operator, setOperator] = useState("");
    const [isResultDisplayed, setIsResultDisplayed] = useState(false);
    const [matrixResult, setMatrixResult] = useState("");

    const openHelpModal = () => setIsHelpOpen(true);
    const closeHelpModal = () => setIsHelpOpen(false);

    const handleSpecialButtonClick = (value) => {
        if (value === "/" && selectedType === "RATIONAL" && !currentInput.includes("/") && currentInput !== "") {
            setCurrentInput((prev) => prev + "/");
        } else if (value === "." && !currentInput.includes(".") && (selectedType === "REAL" || selectedType === "COMPLEX")) {
            setCurrentInput((prev) => prev + (prev === "" ? "0." : "."));
        } else if (value === "i" && selectedType === "COMPLEX" && !currentInput.includes("i")) {
            setCurrentInput((prev) => prev + "i");
        }
    };

    const toggleNegative = () => {
        if(currentInput === ""){
            setCurrentInput("-");
        } else if(currentInput === "-"){
            setCurrentInput("");
        } else if(currentInput.startsWith("-")){
            setCurrentInput(currentInput.substring(1));
        } else {
            setCurrentInput("-" + currentInput);
        }
    };

    const handleEquals = () => {
        setIsResultDisplayed(true);
        return calculate();
    };

    const handleClear = () => {
        clear();
        setIsResultDisplayed(false);
    };

    const handleOperator = async (value) => {
        console.log(value);
        if (firstOperand && currentInput){
            await calculate();
        }
        if(currentInput){
            setFirstOperand(currentInput);
            setCurrentInput("");
        }
        setOperator(value);
    };

    const updateOperand = (value) => {
        console.log(value);
        if(isResultDisplayed) {
            setCurrentInput(value);
            setIsResultDisplayed(false);
        } else {
            setCurrentInput(prev => prev + value);
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
                setFirstOperand(result);
                setCurrentInput("");
                setOperator("");
            } catch (error) {
                console.error("Error during API call :", error);
            }
        }
    };

    const clear = () => {
        setFirstOperand("");
        setCurrentInput("");
        setOperator("");
    };

    const btnHead = [
        {value: "+/-", className: "otherBtn", onClick: toggleNegative},
        {value: "?", className: "helpBtn", onClick: openHelpModal}]

    const btnValues = [
        [
            {value: "7", onClick: () => updateOperand("7")},
            {value: "8", onClick: () => updateOperand("8")},
            {value: "9", onClick: () => updateOperand("9")},
            {value:"+", className: "operation", onClick: () => handleOperator("+")}
        ],
        [
            {value: "4", onClick: () => updateOperand("4")},
            {value: "5", onClick: () => updateOperand("5")},
            {value: "6", onClick: () => updateOperand("6")},
            {value:"-", className: "operation", onClick: () => handleOperator("-")}
        ],
        [
            {value: "1", onClick: () => updateOperand("1")},
            {value: "2", onClick: () => updateOperand("2")},
            {value: "3", onClick: () => updateOperand("3")},
            {value:"*", className: "operation", onClick: () => handleOperator("*")}
        ],
        [
            {value: "C", className: "cBtn", onClick: () => handleClear()},
            {value: "0", onClick: () => updateOperand("0")},
            {value: "=", className: "equals", onClick: () => handleEquals()},
            {value:"/", className: "operation", onClick: () => handleOperator("/")}],
    ];

    const typesNumber = [
        {value: "INTEGER", label: "Integer"},
        {value: "RATIONAL", label: "Rational"},
        {value: "REAL", label: "Real"},
        {value: "COMPLEX", label: "Complex"},
        {value: "MATRIX", label: "Matrix"},
        {value: "UNIT_CONVERSIONS", label: "Unit conversions"}
    ];

    const specialButtonsByType = {
        REAL: [{ value: ".", className: "specialBtn" }],
        RATIONAL: [{ value: "/", className: "specialBtn" }],
        COMPLEX: [{ value: ".", className: "specialBtn" }, { value: "i", className: "specialBtn" }],
    };

    const getSpecialButtons = () => specialButtonsByType[selectedType] || [];


    return <Wrapper>
        <Head><Screen value={matrixResult || currentInput || firstOperand || "0"} />
            {btnHead.map((btn) => (
                <Button
                    key={btn.value}
                    className={btn.className}
                    value={btn.value}
                    onClick={btn.onClick}
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

        {(selectedType === "INTEGER" || selectedType === "COMPLEX" || selectedType === "RATIONAL" || selectedType === "REAL") && (
            <ButtonBox>
                {btnValues.flat().map((btn) => (
                    <Button
                        key={btn.value}
                        className={btn.className}
                        value={btn.value}
                        onClick={btn.onClick}
                    />
                ))}
            </ButtonBox>
        )}

        {selectedType === "MATRIX" && <Matrix setMatrixResult={setMatrixResult} />}
        {selectedType === "UNIT_CONVERSIONS" && <Converter/>}



        <HelpModal isOpen={isHelpOpen} onClose={closeHelpModal} ></HelpModal>
    </Wrapper>;
};

export default Calculator;