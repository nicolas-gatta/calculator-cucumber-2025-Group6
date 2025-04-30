import "./TypeBar.css"
import PropTypes from "prop-types";

const TypeBar = ({ children }) => {
    return <div className="typeBar">{children}</div>;
};

TypeBar.propTypes = {
    children: PropTypes.node.isRequired
}

export default TypeBar;


