import { useState } from "react";
import ConverterBase from "./ConverterBase";
import Button from "./Button";
import Modal from "./Modal";

import "./ProgrammerConverter.css"
import PropTypes from "prop-types";

const ProgrammerConverter = ({ setProgrammerConverterResult }) => {
    const [isModalOpen, setIsModalOpen] = useState(false);

    const units = [
        { value: "DECIMAL", label: "Decimal (base 10)" },
        { value: "BINARY", label: "Binary (base 2)" },
        { value: "OCTAL", label: "Octal (base 8)" },
        { value: "HEXADECIMAL", label: "Hexadecimal (base 16)" },
    ];

    return (
        <div className="programmer-converter">

            <Button value="ℹ️ Infos formats" onClick={() => setIsModalOpen(true)} />

            <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="ℹ️ Available Formats">
                <br />
                <ul>
                    <li><strong>Decimal:</strong></li>
                    <ul>
                        <li>Digits from 0 to 9</li>
                    </ul>
                    <br />
                    <li><strong>Binary:</strong></li>
                    <ul>
                        <li>Digits 0 and 1</li>
                        <li>You can prepend "0b"</li>
                    </ul>
                    <br />
                    <li><strong>Octal:</strong></li>
                    <ul>
                        <li>Digits from 0 to 7</li>
                        <li>You can prepend "0"</li>
                    </ul>
                    <br />
                    <li><strong>Hexadecimal:</strong></li>
                    <ul>
                        <li>Digits from 0 to 9 and letters A to F (uppercase)</li>
                        <li>You can prepend "0x"</li>
                    </ul>
                </ul>
            </Modal>

            <ConverterBase
                units={units}
                apiPath="/api/programmer/convert"
                conversionType="PROGRAMMER"
                setConverterBaseResult={setProgrammerConverterResult}
            />
        </div>
    );
};

ProgrammerConverter.propTypes = {
    setProgrammerConverterResult: PropTypes.func.isRequired
}

export default ProgrammerConverter;
