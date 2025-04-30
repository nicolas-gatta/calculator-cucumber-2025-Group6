import "./ButtonBox.css";
import PropTypes from "prop-types"

const ButtonBox = ({ children }) => {
    return <div className="buttonBox">{children}</div>;
};

ButtonBox.propTypes = {
    children: PropTypes.node.isRequired,
}

export default ButtonBox;