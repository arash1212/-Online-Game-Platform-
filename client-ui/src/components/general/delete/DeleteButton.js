import { useEffect, useState } from 'react'
import './DeleteButton.css'

export default function DeleteButton(props) {
    const [id, setId] = useState(0);
    const styles = {
        width: props.width
    }

    useEffect(() => {
        setId(props.id);
    }, [])
    return (
        <>
            {console.log('id : ' + id)}
            <button
                className="delete-btn-main"
                onClick={() => props.handleClick(id)}
                style={styles}>
                حذف
            </button>
        </>
    )
}