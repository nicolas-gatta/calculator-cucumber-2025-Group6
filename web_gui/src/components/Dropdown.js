import "./Dropdown.css";
import PropTypes from "prop-types";

const Dropdown = ({ title, options, selectedOption, onChange}) => {
    return (
        <div className={"dropdown"}>
            <label className={"dropdown-label"}>{title} :</label>
            <select className={"dropdown-select"} value={selectedOption} onChange={(e) => onChange(e.target.value)}>
                {options.map((opt) => (
                    <option key={opt.value} value={opt.value}>
                        {opt.label}
                    </option>
                ))}
            </select>
        </div>
    );
};

Dropdown.propTypes = {
    title: PropTypes.string.isRequired,
    options: PropTypes.arrayOf(
        PropTypes.shape({
            value: PropTypes.string.isRequired,
            label: PropTypes.string.isRequired,
        })
    ).isRequired,
    selectedOption: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
}

export default Dropdown;