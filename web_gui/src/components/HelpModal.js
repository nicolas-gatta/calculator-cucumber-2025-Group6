import "./HelpModal.css"
import Modal from "./Modal";

const HelpModal = ({isOpen, onClose}) => {
    if (!isOpen) return null;
    return (
        <Modal title={"How to use the calculator"} isOpen={isOpen} onClose={onClose}>
            <h3>Scientific Calculator Instructions</h3>
            <br/>
            <h4 className={"types"}>Number Types :</h4>
            <br/>
            <div className={"content-list"}>
                <ul>
                    <li>Integer: Whole numbers (e.g., 42, -7)</li>
                    <li>Rational: Fractions (e.g., 3/4, -5/2)</li>
                    <li>Real: Decimal numbers (e.g., 3.14, -0.5)</li>
                    <li>Complex: Numbers with real and imaginary parts (e.g., 3+4i, 5i)</li>
                </ul>
            </div>
            <br/>
            <h4 className={"usage"}>Basic Usage :</h4>
            <br/>
            <div className={"content-list"}>
                <ol>
                    <li>Select the number type from the dropdown</li>
                    <li>Enter the first number</li>
                    <li>Click an operation button (+, -, *, /)</li>
                    <li>Enter the second number</li>
                    <li>Click = to see the result</li>
                </ol>
            </div>
            <br/>
            <h4 className={"features"}>SpecialFeatures :</h4>
            <br/>
            <div className={"content-list"}>
                <ul>
                    <li>+/- button: Toggle between positive and negative</li>
                    <li>C button: Clear the calculator</li>
                    <li>/ button (Rational): Add fraction separator</li>
                    <li>. button (Real/Complex): Add decimal point</li>
                    <li>i button (Complex): Add imaginary unit</li>
                </ul>
            </div>

        </Modal>
    )
};

export default HelpModal;