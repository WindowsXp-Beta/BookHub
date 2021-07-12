import React from 'react';
import {Button, DatePicker, Input, message, Table} from 'antd'
import {SearchOutlined} from "@ant-design/icons";
import Highlighter from "react-highlight-words";
import {getBestCustomers} from "../../services/orderService";

const { RangePicker } = DatePicker;

export class BestCustomerTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            showData:[],
            searchText: '',
            searchedColumn: '',
        };
    }

    componentDidMount() {
        const callback = (data) => {
            for(let i = 0; i < data.length; i++) {
                data[i].sum = data[i].sum / 100;
            }
            this.setState({
                showData: data,
            });
        }
            let user = JSON.parse(localStorage.getItem('user'));
            if (user === null) {
                message.error("请登录");
            } else {
                getBestCustomers({"timeStart": null}, callback);
            }
        };
            // console.log(data);
            // let number = new Array(40);
            // let sum = new Array(40);
            // let arr =[];
            // for(let index = 0; index < 40; index++){
            //     number[index] = 0;
            //     sum[index] = 0;
            // }
            // for(let i = 0;i <= data.length - 1; i++)
            // {
            //     let userId = data[i].userId;
            //     for(let j = 0; j <= data[i].orderItem.length - 1; j++)
            //     {
            //         let item = data[i].orderItem[j];
            //         number[userId] += item.bookNum;
            //         sum[userId] += Number(item.bookNum * item.book.price / 100);
            //     }
            // }
            // for(let index = 0; index < 40; index++){
            //     if(number[index] !== 0)
            //     {
            //         let json = {
            //             userId: index,
            //             bookNumber: number[index],
            //             sum: sum[index].toFixed(1)};
            //         arr.push(json)
            //     }
            // }

    getColumnSearchProps = dataIndex => ({
        filterDropdown: ({setSelectedKeys, selectedKeys, confirm, clearFilters}) => (
            <div style={{padding: 8}}>
                <Input
                    ref={node => {
                        this.searchInput = node;
                    }}
                    placeholder={`Search ${dataIndex}`}
                    value={selectedKeys[0]}
                    onChange={e => setSelectedKeys(e.target.value ? [e.target.value] : [])}
                    onPressEnter={() => this.handleSearch(selectedKeys, confirm, dataIndex)}
                    style={{width: 188, marginBottom: 8, display: 'block'}}
                />
                <Button
                    type="primary"
                    onClick={() => this.handleSearch(selectedKeys, confirm, dataIndex)}
                    icon={<SearchOutlined/>}
                    size="small"
                    style={{width: 90, marginRight: 8}}
                >
                    Search
                </Button>
                <Button onClick={() => this.handleReset(clearFilters)} size="small" style={{width: 90}}>
                    Reset
                </Button>
            </div>
        ),

        filterIcon: filtered => <SearchOutlined style={{color: filtered ? '#1890ff' : undefined}}/>,

        onFilter: (value, record) => record[dataIndex]?
            record[dataIndex].toString().toLowerCase().includes(value.toLowerCase()) : '',

        onFilterDropdownVisibleChange: visible => {
            if (visible) {
                setTimeout(() => this.searchInput.select());
            }
        },

        render: text =>
            this.state.searchedColumn === dataIndex ? (
                <Highlighter
                    highlightStyle={{backgroundColor: '#ffc069', padding: 0}}
                    searchWords={[this.state.searchText]}
                    autoEscape
                    textToHighlight={text.toString()}
                />
            ) : (
                text
            ),
    });
    handleSearch = (selectedKeys, confirm, dataIndex) => {
        confirm();
        this.setState({
            searchText: selectedKeys[0],
            searchedColumn: dataIndex,
        });
    };
    handleReset = clearFilters => {
        clearFilters();
        this.setState({searchText: ''});
    };

    timeChange = (value, dateString) => {
        if( dateString[0] === '' || dateString[1] === '')
        {
            this.setState({showData:this.state.data})
            return;
        }
        console.log('Formatted Selected Time: ', dateString);
        let timeRange = {
            timeStart: dateString[0],
            timeEnd: dateString[1]
        };
        const callback = (data) => {
            console.log(data);
            for(let i = 0; i < data.length; i++){
                data[i].sum = data[i].sum / 100;
            }
            this.setState({
                showData:data
            })
        };
        let user = JSON.parse(localStorage.getItem('user'));
        if (user === null) {
            message.error("请登录");
        } else {
            getBestCustomers(timeRange, callback);
        }

    }
        // const startTime= new Date(Date.parse(dateString[0]));
        // const endTime=new Date(Date.parse(dateString[1]));
        // let data = this.state.orders;
        // let number = new Array(40);
        // let sum = new Array(40);
        // let arr =[];
        // for(let index = 0;index < 40;index++){
        //     number[index] = 0;
        //     sum[index] = 0;
        // }
        // for(let i = 0; i <= data.length - 1; i++)
        // {
        //     let userId = data[i].userId;
        //     let time = new Date(Date.parse(data[i].time));
        //     if (time > startTime && time < endTime) {
        //         for (let j = 0; j <= data[i].orderItem.length - 1; j++) {
        //             let item = data[i].orderItem[j];
        //             number[userId] += item.bookNum;
        //             sum[userId] += Number(item.bookNum * item.book.price / 100);
        //         }
        //     }
        // }
        // for(let index = 0;index < 40;index++){
        //     if(number[index] !== 0)
        //     {
        //         let json = {
        //             userId: index,
        //             bookNumber: number[index],
        //             sum: sum[index].toFixed(1)};
        //         arr.push(json)
        //     }
        // }

    render() {
        const columns = [
            {
                title: 'userId',
                dataIndex: 'userId',
                key: 'userId',
                ...this.getColumnSearchProps('userId'),
            },
            {
                title: 'Sum',
                dataIndex: 'sum',
                key: 'sum',
                sorter: (a, b) => a.sum - b.sum,
            },
        ];

        return (
            <div>
                <br/>
                <br/>
                <RangePicker onChange ={this.timeChange}/>
                <Table bordered columns={columns} dataSource={this.state.showData} />
            </div>
        );
    }
}
