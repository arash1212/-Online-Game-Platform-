import { useState, useEffect } from 'react';
import { findAll, remove } from '../../../service/user/UsersService';
import ConfirmButton from '../../general/buttons/confirm/ConfirmButton';
import DeleteButton from '../../general/delete/DeleteButton';
import Table from '../../general/table/Table';
import './UsersTable.css'

export default function UsersTable(props) {

    const [data, setData] = useState([]);
    const [tableData, setTableData] = useState([]);

    function handleRemove(id) {
        remove(id);
        getTableData();
    }

    let tableHeadData = [
        "شناسه",
        "ایمیل",
        "موبایل",
        "نام اکانت",
        "رمز عبور",
        "زمان ایجاد",
        "بلاک نشده",
        "فعال",
        "حذف",
        "بروزرسانی"
    ];

    function getTableData() {
        findAll()
            .then(response => {
                setData(response.data);
            }).catch(error => console.log(error));

        let data2 = [];
        Object.keys(data).forEach((index) => {
            data2.push(
                {
                    "id": data[index].id,
                    "email": data[index].email,
                    "mobile": data[index].mobile,
                    "accountName": data[index].accountName,
                    "password": data[index].password,
                    "creationTime": data[index].creationTime,
                    "accountNonLocked": data[index].accountNonLocked,
                    "enabled": data[index].enabled,
                    "delete": <DeleteButton id={data[index].id} handleClick={handleRemove} />,
                    "update": <ConfirmButton value='بروزرسانی' width='80px' />
                }
            )
        })
        setTableData(data2);
    }

    useEffect(() => {
        getTableData();
    }, []);

    return (
        <div className='users-table-main'>
            <button className='refresh-button' onClick={getTableData}>Refresh Table</button><br />
            <Table headData={tableHeadData} tableData={tableData} />
        </div>
    )
}