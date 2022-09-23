import './ConfirmButton.css'

export default function ConfirmButton(props) {
    const styles = {
        width: props.width
    }
    return (
        <>
            <button 
            className="confirm-btn-main" 
            onClick={props.handleClick}
            style={styles}>
                {props.value}
            </button>
        </>
    )
}