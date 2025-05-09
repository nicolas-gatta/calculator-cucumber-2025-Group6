import "./ConverterBase.css";
import Dropdown from "./Dropdown";
import {useEffect, useState} from "react";
import Field from "./Field";
import Button from "./Button";
import PropTypes from "prop-types";


const ConverterBase = ({ units, conversionType, apiPath }) => {
    const [fromUnit, setFromUnit] = useState("");
    const [toUnit, setToUnit] = useState("");
    const [value, setValue] = useState('');
    const [result, setResult] = useState('');

    useEffect(() => {
        setFromUnit(units[0]?.value || "");
        setToUnit(units[1]?.value || "");
    }, [units]);

    useEffect(() => {
        if (value === "") setResult("");
    }, [value]);


    const doConversion = async () => {
        if (!value || !fromUnit || !toUnit || !conversionType || !apiPath) return;

        try {
            const response = await fetch(apiPath, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    conversionType,
                    fromUnit,
                    toUnit,
                    value,
                }),
            });

            if (!response.ok) {
                console.error("Conversion failed");
                setResult("Error");
                return;
            }

            const resultText = await response.text();
            setResult(resultText);
        } catch (error) {
            console.error("Error during conversion:", error);
            setResult("Error");
        }
    };

    useEffect(() => {
        if (
            value && fromUnit && toUnit &&
            conversionType && apiPath &&
            units.some(unit => unit.value === fromUnit) &&
            units.some(unit => unit.value === toUnit)
        ) {
            void doConversion();
        }
    }, [value, fromUnit, toUnit, conversionType, apiPath, units]);

    const handleClear = () => {
        setValue('');
        setResult('');
    }

    return (
        <div className="converter-base">
            {/* From Dropdown */}
            <Dropdown title={"From"} options={units} selectedOption={fromUnit} onChange={setFromUnit}/>

            {/* To Dropdown */}
            <Dropdown title={"To"} options={units} selectedOption={toUnit} onChange={setToUnit}/>
            <Field title="Value" value={value} onChange={setValue}/>
            <Field title="Result" value={result} readOnly/>
            {result === "Error" && (
                <div className="error-text">An error occurred. Please check your input.</div>
            )}
            <Button value="Clear" onClick={handleClear}/>
        </div>
    );
};

ConverterBase.propTypes = {
    units: PropTypes.arrayOf(
        PropTypes.shape({
            value: PropTypes.string.isRequired,
            label: PropTypes.string.isRequired,
        })
    ).isRequired,
    conversionType: PropTypes.string,
    apiPath: PropTypes.string.isRequired
}

export default ConverterBase;