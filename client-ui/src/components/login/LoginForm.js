import { useState } from 'react';
import './LoginForm.css'
import Input from '../general/inputs/Input'
import ConfirmButton from '../general/buttons/ConfirmButton';

export default function LoginForm(props) {

    const marginBottom = '15px';
    const width = '450px';
    const btnWidth = '350px';

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    let postObject = {
        email: email,
        password: password
    }

    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(postObject)
    };

    function handlePost(e) {
        e.preventDefault();
        console.log(JSON.stringify(postObject));
        fetch('http://localhost:8080/api/pub/auth/jwt', requestOptions)
            .then(response => response.json())
            .then(result => console.log(result));
    }

    return (
        <>
            <h1 className='user-login-h1'>ورود</h1>
            <div className="user-login-main">
                <form onSubmit={e => handlePost(e)}>
                    <Input
                        id='email'
                        type='text'
                        label='آدرس ایمیل'
                        name='email'
                        width={width}
                        bottom={marginBottom}
                        setValue={setEmail}
                    /><br />

                    <Input
                        id='password'
                        type='password'
                        label='رمز عبور'
                        name='email'
                        width={width}
                        bottom={marginBottom}
                        setValue={setPassword}
                    />
                    <br />

                    <ConfirmButton
                        value='ورود'
                        handleClick={null}
                        width={btnWidth}
                    />
                </form>
            </div>
        </>
    )
}