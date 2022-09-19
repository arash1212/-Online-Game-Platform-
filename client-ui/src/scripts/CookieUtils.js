import Cookies from 'universal-cookie';

export default function SetCookie(key = '', value = '', ttl = 1) {
    const cookies = new Cookies();
    let date = new Date();
    date.setTime(date.getTime() + (60 * 60 * 1000));
    cookies.set(key, value, { path: '/', expires: date });
    console.log('cookie value : ' + cookies.get(key));
}