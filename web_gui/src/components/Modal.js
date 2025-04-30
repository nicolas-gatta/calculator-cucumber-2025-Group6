import "./Modal.css";

const Modal = ({ isOpen, onClose, children, title }) => {
    if (!isOpen) return null; // Si pas ouvert, ne rend rien

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

export default Modal;