import React from 'react';
import "./Field.css"
import PropTypes from "prop-types";

const Field = ({ title, value, onChange, readOnly }) => {
    return (
        <div className="field">
            <label className="field-label">{title} :</label>
            <input
                type="text"
                value={value}
                onChange={(e) => onChange(e.target.value)}
                className="field-input"
                readOnly={readOnly}
            />
        </div>
    );
};

Field.propTypes = {
    title: PropTypes.string.isRequired,
    value: PropTypes.oneOfType([
        PropTypes.string,
        PropTypes.number
    ]).isRequired,
    onChange: PropTypes.func,
    readOnly: PropTypes.bool
};

Field.defaultProps = {
    readOnly: false
};

export default Field;

