import { useState } from 'react';
import UsersService from '../../../service/user/UsersService';
import Table from '../../general/table/Table';
import './UsersTable.css'

export default function UsersTable(props) {

    const [tableData, setTableData] = useState([]);

    async function getTableData() {
        UsersService.findAll()
            .then(response => {
                setTableData(response.data);
            }).catch(error => console.log(error));
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