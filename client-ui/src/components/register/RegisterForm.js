import { useState } from "react";
import ConfirmButton from "../general/buttons/ConfirmButton";
import Input from "../general/inputs/Input"
import './RegisterForm.css'

export default function RegisterForm(props) {
    const marginBottom = '40px';
    const width = '260px';

    const [email, setEmail] = useState('');
    const [mobile, setMobile] = useState('');
    const [accountName, setAccountName] = useState('');
    const [password, setPassword] = useState('');
    const [passwordRepeat, setPasswordRepeat] = useState('');

    function checkPassword() {
        if (password !== passwordRepeat) {
            alert('رمز عبور و تکرار آن باید برابر باشند.');
        }
    }

    return (
        <div className="user-register-main">
            <h1>ایجاد حساب کاربری جدید</h1>
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

        </div>
    )
}