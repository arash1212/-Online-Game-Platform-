import React from "react";
import { useState } from "react";
import './Input.css'

export default function Input(props) {
    const [value, setValue] = useState('');


    const styles = {
        marginBottom: props.bottom,
        width: props.width
    }

    function onChangeHandler(e) {
        setValue(e.target.value);
    }

    return (
        <>
            <label
                htmlFor={props.id}
                className="input-label">{props.label}
            </label><br />

            <input className="input"
                id={props.id}
                type={props.type}
                name={props.name}
                onChange={e => props.setValue(e.target.value)}
                style={styles}
            />
        </>
    )
}