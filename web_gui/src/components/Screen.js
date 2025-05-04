import "./Screen.css";
import PropTypes from "prop-types";

const Screen = ({ value }) => {
    return (
        <div className="screen">
            <span className="screen-text">{value}</span>
        </div>
    );
};

Screen.propTypes = {
    value: PropTypes.string.isRequired
}

export default Screen;