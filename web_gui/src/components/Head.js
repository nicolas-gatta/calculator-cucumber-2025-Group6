import "./Head.css"
import PropTypes from "prop-types";

const Head = ({ children }) => {
    return <div className="head">{children}</div>;
};

Head.propTypes = {
    children: PropTypes.node.isRequired
}

export default Head;