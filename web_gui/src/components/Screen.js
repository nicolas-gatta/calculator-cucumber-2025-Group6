import "./Screen.css";

const Screen = ({ value }) => {
    return (
        <div className="screen">
            <span className="screen-text">{value}</span>
        </div>
    );
};

export default Screen;