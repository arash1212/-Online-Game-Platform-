import { useState } from "react";
import UsersService from "../../../service/user/UsersService";
import ConfirmButton from "../../general/buttons/ConfirmButton";
import Input from "../../general/inputs/Input"
import './UserRegisterForm.css'

export default function UserRegisterForm(props) {
    const marginBottom = '45px';
    const width = '290px';

    const [email, setEmail] = useState('');
    const [mobile, setMobile] = useState('');
    const [accountName, setAccountName] = useState('');
    const [password, setPassword] = useState('');
    const [passwordRepeat, setPasswordRepeat] = useState('');

    let postData = {
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

    function handlePost(e) {
        e.preventDefault();
        UsersService.create(postData)
            .then(response => console.log(response))
            .catch(error => console.log(error));
    }

    return (
        <div className="user-register-main">
            <h1>ایجاد حساب کاربری جدید</h1><br />

            <form onSubmit={e => handlePost(e)}>
                {console.log(postData)}
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
                    id='mobile'
                    type='text'
                    label='شماره موبایل'
                    name='mobile'
                    width={width}
                    bottom={marginBottom}
                    setValue={setMobile}
                />

                <Input
                    id='accountName'
                    type='text'
                    label='نام حساب کاربری'
                    name='accountName'
                    width={width}
                    bottom={marginBottom}
                    setValue={setAccountName}
                />

                <Input
                    id='password'
                    type='password'
                    label='رمز عبور'
                    name='password'
                    width={width}
                    bottom={marginBottom}
                    setValue={setPassword}
                />

                <Input
                    id='password-repeat'
                    type='password'
                    label='تکرار رمز عبور'
                    name='password-repeat'
                    width={width}
                    bottom={marginBottom}
                    setValue={setPasswordRepeat}
                />

                <ConfirmButton value='ثبت نام' handleClick={checkPassword} />
            </form>
        </div>


    )
}