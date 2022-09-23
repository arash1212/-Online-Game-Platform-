import './Sidebar.css'

export default function Sidebar(props) {
    return (
        <div className="side-bar-main">
            <ul className='side-bar-main-menu'>
                <li className='side-bar-menu-item'>
                    <a href='/users-list'>کاربران</a>
                </li>
                <li className='side-bar-menu-item'>
                    <a href='/users-list'>امنیت</a>
                </li>
            </ul>
        </div >
    )
}