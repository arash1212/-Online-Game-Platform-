import './Header.css'

export default function Header() {
    const menuItems = [
        {
            value: "فروشگاه",
            link: "."
        },
        {
            value: "بخش اجتماعی",
            link: "."
        },
        {
            value: "درباره ما",
            link: "."
        },
        {
            value: "پشتیبانی",
            link: "."
        }
    ];

    return (
        <div className="main-div">
            <a href='.'>
                <img className='logo-img' src='images/logo_store.png' alt='storeLogo' />
            </a>
            <ul className='nav'>
                {
                    menuItems.map((item) => {
                        return (
                            <li className='nav-link'>
                                <a href={item.link}>{item.value}</a>
                            </li>
                        )
                    })
                }
            </ul>
        </div>
    )
}