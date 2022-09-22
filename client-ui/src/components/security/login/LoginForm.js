import { useState } from 'react';
import './LoginForm.css'
import Input from '../../general/inputs/Input'
import ConfirmButton from '../../general/buttons/ConfirmButton';
import SetCookie from '../../../scripts/CookieUtils'
import { loginJwt } from '../../../service/security/SecurityAuthenticationService';

export default function LoginForm(props) {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [token, setToken] = useState('');

    const marginBottom = '15px';
    const width = '450px';
    const btnWidth = '350px';

    let postData = {
        email: email,
        password: password
    }

    async function handlePost(e) {
        e.preventDefault();
        loginJwt(postData).then(response => {
            setToken(response.data);
        }).catch(error => console.log(error));
        SetCookie('store-jwt-token', token.token, 60);
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