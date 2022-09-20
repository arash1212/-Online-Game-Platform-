import './Table.css'
import React from 'react';

//TODO update beshe
export default function Table(props) {

    let indexes = props.tableData !== null ? Object.keys(props.tableData) : Object.keys([{ "testField": "test" }]);
    let tableHeadData = Object.values(props.headData);
    let tableDatas = props.tableData !== null ? props.tableData : Object.values([{ "testField": "test" }]);

    return (
        <table className='table-main' border={1}>
            <thead>
                <tr>
                    {
                        tableHeadData.map(header => {
                            return <th key={header}>{header}</th>
                        })
                    }
                </tr>
            </thead>

            <tbody>
                {
                    indexes.map(index => {
                        var i = 0
                        return (
                            <tr key={index}>
                                {
                                    Object.values(tableDatas[index]).map(value => {
                                        return <td key={i++}>{JSON.stringify(value)}</td>
                                    })
                                }
                            </tr>
                        )
                    })
                }
            </tbody>

        </table >
    )
}