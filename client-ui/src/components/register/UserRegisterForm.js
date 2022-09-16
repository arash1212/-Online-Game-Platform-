import { useState } from "react";
import ConfirmButton from "../general/buttons/ConfirmButton";
import Input from "../general/inputs/Input"
import './UserRegisterForm.css'

export default function RegisterForm(props) {
    const marginBottom = '45px';
    const width = '290px';

    const [email, setEmail] = useState('');
    const [mobile, setMobile] = useState('');
    const [accountName, setAccountName] = useState('');
    const [password, setPassword] = useState('');
    const [passwordRepeat, setPasswordRepeat] = useState('');


    let postObject = {
        email: email,
        mobile: mobile,
        accountName: accountName,
        password: password
    }

    function checkPassword() {
        if (password !== passwordRepeat) {
            alert('رمز عبور و تکرار آن باید برابر باشند.');
        }
    }

    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(postObject)
    };

    function handlePost(e) {
        e.preventDefault();
        fetch('http://localhost:8080/api/pub/users', requestOptions)
            .then(response => response.json());
    }

    return (
        <div className="user-register-main">
            <h1>ایجاد حساب کاربری جدید</h1><br />
            <form onSubmit={e => handlePost(e)}>
                {console.log(postObject)}
                <Input
                    id='email'
                    type='text'
                    label='آدرس ایمیل'
                    name='email'
                    width={width}
                    bottom={marginBottom}
                    setValue={setEmail}
                />
                <br />

                <Input
                    id='mobile'
                    type='text'
                    label='شماره موبایل'
                    name='mobile'
                    width={width}
                    bottom={marginBottom}
                    setValue={setMobile}
                />
                <br />

                <Input
                    id='accountName'
                    type='text'
                    label='نام حساب کاربری'
                    name='accountName'
                    width={width}
                    bottom={marginBottom}
                    setValue={setAccountName}
                />
                <br />

                <Input
                    id='password'
                    type='password'
                    label='رمز عبور'
                    name='password'
                    width={width}
                    bottom={marginBottom}
                    setValue={setPassword}
                />
                <br />

                <Input
                    id='password-repeat'
                    type='password'
                    label='تکرار رمز عبور'
                    name='password-repeat'
                    width={width}
                    bottom={marginBottom}
                    setValue={setPasswordRepeat}
                />
                <br />

                <ConfirmButton value='ثبت نام' handleClick={checkPassword} />
            </form>
        </div>
    )
}