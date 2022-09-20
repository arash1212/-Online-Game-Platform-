import { useState } from 'react';
import { Get } from '../../scripts/RestClientUtils'
import Table from '../general/table/Table';
import './UsersTable.css'

export default function UsersTable(props) {

    const [tableData, setTableData] = useState([]);

    async function getTableData() {
        let reponse = await Get('http://localhost:8080/api/pub/users');
        setTableData(reponse);
        return reponse;

    }

    let tableHeadData = [
        "ایمیل",
        "موبایل",
        "رمز عبور",
        "نام اکانت",
        "شناسه",
        "زمان ایجاد",
        "حذف شده",
        "اکانت منقضی نشده",
        "بلاک نشده",
        "منقضی نشدن اطلاعات ورود",
        "فعال",
        "دسترسی ها"
    ];

    return (
        <div className='users-table-main'>
            <button onClick={getTableData}>Refresh Table</button><br />
            <Table headData={tableHeadData} tableData={tableData} />
        </div>
    )
}