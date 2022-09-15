import './ConfirmButton.css'

export default function ConfirmButton(props) {
    return (
        <>
            <button className="confirm-btn-main" onClick={props.handleClick}>
                {props.value}
            </button>
        </>
    )
}