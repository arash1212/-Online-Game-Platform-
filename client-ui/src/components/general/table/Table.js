import './Table.css'

//TODO update beshe
export default function Table(props) {

    let dataIndexes = props.tableData !== null ? Object.keys(props.tableData) : Object.keys([{ "testField": "test" }]);
    let tableHeadData = Object.values(props.headData);
    let tableDatas = props.tableData !== null ? props.tableData : Object.values([{ "testField": "test" }]);

    return (
        <table className='table-main'>
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
                    dataIndexes.map(index => {
                        var i = 0
                        return (
                            <tr key={index}>
                                {
                                    Object.values(tableDatas[index]).map(value => {
                                        if (typeof (value) == 'boolean')
                                            return <td key={i++}>{String(value)}</td>
                                        else return <td key={i++}>{value}</td>
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