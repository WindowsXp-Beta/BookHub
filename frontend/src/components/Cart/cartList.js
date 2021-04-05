import React from 'react';
import {Button, Input, InputNumber, List, message, Popconfirm, Table} from 'antd';


const {Search} = Input;

const data = [
    {
        key: '1',
        title: 'Computer Systems',
        bookNumber: '1',
        bookPrice: '120',
        sum: '120',
    },
    {
        key: '2',
        title: 'Operating Systems',
        bookNumber: '2',
        bookPrice: '100',
        sum: '200',
    },
    {
        key: '3',
        title: 'Algorithm',
        bookNumber: '3',
        bookPrice: '80',
        sum: '240',
    },
];
export class CartList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            cart: data,
            showCart: data,
            searchValue: '',
            selectedRowKeys: [],
        }
    }

    deleteConfirm = (key) => {
        const data = this.state.cart;

        for (let i = 0; i < data.length; ++i) {
            if (data[i].key === key) {
                data.splice(i, 1);
                break;
            }
        }
        let tmp = [...data];

        // this.sendDeletion(key);
        this.setState(() => ({data: tmp}));
    };

    searchChange = ({target: {value}}) => {

        this.setState({searchValue: value})
        let arr = [];
        let list = this.state.cart;
        let search = value.toLowerCase();

        for (let i = 0; i < list.length; i++) {
            if (
                list[i].title.toLowerCase().indexOf(search) >= 0
            ) {
                arr.push(list[i]);
            }
        }
        this.setState(
            {showCart: arr}
        );
    }

    handleNumberChange = (itemId, value) => {
        console.log('changed', itemId, value);
        if (value <= 0) {
            message.error('数目必须大于0！');
            return;
        }
        let json = {
            itemId: itemId,
            bookNumber: value
        };
        let tmp = this.state.cart;
        debugger;
        for (let i = 0; i < tmp.length; ++i) {
            if (tmp[i].key === itemId) {
                tmp[i].bookNumber = value;
                tmp[i].sum = (tmp[i].bookPrice * tmp[i].bookNumber).toFixed(1);
            }
        }
        this.setState({cart: tmp, showCart: tmp});
        const callback = () => {
            message.success('修改成功');
        }
        // editCartItemNumber(json, callback);
    }

    render() {
        const columns = [
            {
                title: 'Title',
                dataIndex: 'title',
                key: 'title',
            },
            {
                title: 'Number',
                dataIndex: 'bookNumber',
                key: 'bookNumber',
                render: (text, record) =>
                    (<InputNumber defaultValue={record.bookNumber}
                                  onChange={value => {
                                      this.handleNumberChange(record.key, value);
                                  }
                                  }/>)
            },
            {
                title: 'Price/per',
                dataIndex: 'bookPrice',
                key: 'bookPrice',
            },
            {
                title: 'Sum',
                dataIndex: 'sum',
            },
            {
                title: 'Action',
                dataIndex: 'action',
                render: (text, record) => {
                    return (
                        <Popconfirm
                            title="Are you sure delete this book?"
                            onConfirm={() => this.deleteConfirm(record.key)}
                            okText="Yes"
                            cancelText="No"
                        >
                            <Button>Delete</Button>
                        </Popconfirm>
                    );
                }
            }
        ];

        return (
            <div className="container">
                <br/>
                <Search value={this.state.searchValue} placeholder="search for cartItem" onChange={this.searchChange}
                        enterButton/>
                <br/>
                <br/>
                <Table
                    // rowSelection={rowSelection}
                       columns={columns}
                       dataSource={this.state.showCart}
                />
                <div style={{marginBottom: 16}}>
                    <Button type="primary" onClick={this.submitOrder}
                            // loading={loading}
                    >
                        Submit
                    </Button>
                    {/*<span style={{marginLeft: 8}}>*/}
                    {/*    {hasSelected ? `${this.calItems(selectedRowKeys, selectedRows)} items` : ''}*/}
                    {/*</span>*/}
                    {/*<span style={{marginLeft: 10}}>*/}
                    {/*    {hasSelected ? `Total: ￥${this.totalSum(selectedRowKeys).toFixed(1)} ` : ''}*/}
                    {/*</span>*/}
                </div>
            </div>
        )
    }
}