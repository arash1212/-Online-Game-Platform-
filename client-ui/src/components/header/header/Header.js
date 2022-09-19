import './Header.css'

export default function Header() {
    const menuItems = [
        {
            id: 1,
            value: "فروشگاه",
            link: "."
        },
        {
            id: 2,
            value: "بخش اجتماعی",
            link: "."
        },
        {
            id: 3,
            value: "درباره ما",
            link: "."
        },
        {
            id: 4,
            value: "پشتیبانی",
            link: "."
        }
    ];

    return (
        <div className="header-main-div">
            <a href='.'>
                <img className='logo-img' src='images/logo_store.png' alt='storeLogo' />
            </a>
            <ul className='nav'>
                {
                    menuItems.map((item) => {
                        return (
                            <li key={item.id} className='nav-link'>
                                <a href={item.link}>{item.value}</a>
                            </li>
                        )
                    })
                }
            </ul>

            <div className='login-div'>
                <a className='login-div-item' href='/login'> ورود </a>
                <span className='login-div-item'> | </span>
                <a className='login-div-item' href='/user-register'> ثبت نام تست </a>
            </div>
        </div>
    )
}