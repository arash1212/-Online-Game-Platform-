import { useState } from 'react';
import './LoginForm.css'
import Input from '../general/inputs/Input'
import ConfirmButton from '../general/buttons/ConfirmButton';
import SetCookie from '../../scripts/CookieUtils'

import { Post } from '../../scripts/RestClientUtils'

export default function LoginForm(props) {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const marginBottom = '15px';
    const width = '450px';
    const btnWidth = '350px';

    let postObject = {
        email: email,
        password: password
    }

    let response = {
        token: '',
    }

    async function handlePost(e) {
        e.preventDefault();
        response = await Post('http://localhost:8080/api/pub/auth/jwt', postObject);
        SetCookie('store-jwt-token', response.token, 60);
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
                    />

                    <Input
                        id='password'
                        type='password'
                        label='رمز عبور'
                        name='email'
                        width={width}
                        bottom={marginBottom}
                        setValue={setPassword}
                    />

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