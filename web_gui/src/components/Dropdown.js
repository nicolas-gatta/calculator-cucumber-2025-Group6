import "./Dropdown.css";
import PropTypes from "prop-types";

const Dropdown = ({ title, options, selectedOption, onChange, disabled, isInTypeBar}) => {
    return (
        <div className={isInTypeBar ? "dropdown-type-bar" : "dropdown"}>
            <label className={isInTypeBar ? "dropdown-label-type-bar" : "dropdown-label"}>{title}:</label>
            <select className={isInTypeBar ? "dropdown-select-type-bar" : "dropdown-select"} value={selectedOption} disabled={disabled}
                    onChange={(e) => onChange(e.target.value)}>
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
    disabled: PropTypes.bool,
    isInTypeBar: PropTypes.bool,
}

Dropdown.defaultProps = {
    disabled: false,
    isInTypeBar: false
}

export default Dropdown;