import "./Modal.css";
import PropTypes from "prop-types"

const Modal = ({ isOpen, onClose, children, title }) => {
    if (!isOpen) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <div className="modal-header">

                    <h2>{title}</h2>
                </div>
                <hr/>
                <div className="modal-body">
                    {children}
                </div>
                <button className="modal-close" onClick={onClose}>OK</button>
            </div>
        </div>
    );
};

Modal.propTypes = {
    isOpen: PropTypes.bool.isRequired,
    onClose: PropTypes.func.isRequired,
    children: PropTypes.node.isRequired,
    title: PropTypes.string.isRequired,
};

export default Modal;