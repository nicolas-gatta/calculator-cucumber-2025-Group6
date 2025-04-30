import "./Dropdown.css";

const Dropdown = ({ title, options, selectedOption, onChange}) => {
    return (
        <div className={"dropdown"}>
            <label className={"dropdown-label"}>{title} :</label>
            <select className={"dropdown-select"} value={selectedOption} onChange={(e) => onChange(e.target.value)}>
                {options.map((opt, index) => (
                    <option key={index} value={opt.value}>
                        {opt.label}
                    </option>
                ))}
            </select>
        </div>
    );
};

export default Dropdown;