import "./Wrapper.css"
import PropTypes from "prop-types";

const Wrapper = ({ children }) => {
    return <div className="wrapper">{children}</div>;
};

Wrapper.propTypes = {
    children: PropTypes.node.isRequired
}

export default Wrapper;