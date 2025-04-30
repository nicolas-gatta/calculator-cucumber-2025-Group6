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

const btnHead = [{value: "+/-", className: "otherBtn"}, {value: "?", className: "helpBtn"}, {}, {}]

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

const realBtn = [
    {value: ".", className: "specialBtn"}
];

const rationalBtn = [
    {value: "/", className: "specialBtn"}
];

const complexBtn = [
    {value: ".", className: "specialBtn"},
    {value: "i", className: "specialBtn"}
];

const Calculator = () => {
    const [selectedType, setSelectedType] = useState("INTEGER"); // ← stocke le type sélectionné
    const [isHelpOpen, setIsHelpOpen] = useState(false);

    const openHelpModal = () => setIsHelpOpen(true);
    const closeHelpModal = () => setIsHelpOpen(false);

    // Fonction pour déterminer les boutons spéciaux à afficher
    const getSpecialButtons = () => {
        switch (selectedType) {
            case "REAL":
                return realBtn;
            case "RATIONAL":
                return rationalBtn;
            case "COMPLEX":
                return complexBtn;
            default:
                return []; // INTEGER => pas de bouton spécial
        }
    };

    return <Wrapper>
        <Head><Screen value={"0"}/>
            {
                btnHead.flat().map((btn, i) => {
                    return (
                        <Button
                            key={i}
                            className={btn.className}
                            value={btn.value}
                            onClick={() => {
                                if (btn.value === "?") {
                                    openHelpModal(); // ← ouvre la modal
                                } else {
                                    console.log(`${btn.value} clicked!`);
                                }
                            }}
                        />
                    );
                })
            }
        </Head>
        <TypeBar> <Dropdown title={"Type"} options={typesNumber} selectedOption={selectedType} onChange={(value) => setSelectedType(value)}/>
            {getSpecialButtons().map((btn, i) => (
                <Button
                    key={i}
                    className={btn.className}
                    value={btn.value}
                    onClick={() => {
                        console.log(`${btn.value} clicked!`);
                    }}
                />
            ))}
        </TypeBar>


        <ButtonBox>
            {
                btnValues.flat().map((btn, i) => {
                    return (
                        <Button
                            key={i}
                            className={btn.className}
                            value={btn.value}
                            onClick={() => {
                                console.log(`${btn.value} clicked!`);
                            }}
                        />
                    );
                })
            }
        </ButtonBox>
        <HelpModal isOpen={isHelpOpen} onClose={closeHelpModal} ></HelpModal>
    </Wrapper>;
};

export default Calculator;