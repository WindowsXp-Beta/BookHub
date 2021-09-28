import React from 'react';
import {Button, Input, InputNumber, List, message, Popconfirm, Table} from 'antd';
import {addOrder} from "../../services/orderService";
import {delCartItem, editCartItemNumber, getCart, getCartNumber} from "../../services/cartService";

import {history} from '../../utils/history';

const {Search} = Input;

export class CartList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            cart: [],
            showCart: [],
            searchValue: '',
            selectedRowKeys: [],
            page: 1,
            pageSize: 10,
            totalCartNumber: 0
        }
    }

    dataProcess = (content, size) => {
        let data = [];
        for (let i = 0; i < size; i++) {
            let item = {};
            item.key = content[i].id;
            item.title = content[i].book.name;
            item.bookId = content[i].book.id;
            item.bookNumber = content[i].bookNumber;
            item.bookPrice = content[i].book.price / 100;
            item.sum = (item.bookPrice * content[i].bookNumber).toFixed(1);
            data.push(item);
        }
        console.log(data);
        return data;
    }

    fetchCarts = (page, clear = false, otherHandle = null) => {
        if (otherHandle !== null) {
            otherHandle();
        }
        const callback = (response) => {
            console.log(response.data);
            let content = response.data.content;
            let size = response.data.size;
            let data = this.dataProcess(content, size);
            if (clear) {
                this.setState({
                    cart: data,
                    showCart: data,
                    searchValue: '',
                    selectedRowKeys: [],
                    selectedRows: [],
                });
            } else {
                this.setState({
                    cart: data,
                    showCart: data,
                });
            }
        }
        this.setState({page: page});
        getCart({page: this.state.page - 1, pageSize: this.state.pageSize}, callback);
    }

    componentDidMount() {
        this.fetchCarts(this.state.page);
        const callback = (response) => {
            this.setState({
                totalCartNumber: response.data
            });
        }
        getCartNumber(callback);
    }

    sendDeletion = (id) => {
        const callback = (response) => {
            this.fetchCarts(this.state.page, true, message.success("删除成功"));
        };
        delCartItem(id, callback);
    };

    totalSum = (selectedRowKeys) => {

        let total = 0;
        for (let i = 0; i < selectedRowKeys.length; ++i) {
            for (let j = 0; j < this.state.cart.length; ++j) {
                if (selectedRowKeys[i] === this.state.cart[j].key) {
                    total += Number(this.state.cart[j].sum);
                    break;
                }
            }
        }
        return total;
    };

    calItems = (selectedRowKeys, selectedRow) => {
        let sum = 0;
        for (let i = 0; i < selectedRowKeys.length; ++i) {
            if (selectedRow[i] !== undefined) {
                sum += 1
            }
        }
        return sum;
    }

    deleteConfirm = (key) => {
        console.log(key);
        const data = this.state.cart;

        for (let i = 0; i < data.length; ++i) {
            if (data[i].key === key) {
                data.splice(i, 1);
                break;
            }
        }
        let tmp = [...data];

        this.sendDeletion(key);
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

    handleNumberChange = (id, value) => {
        console.log('changed', id, value);
        if (value <= 0) {
            message.error('数目必须大于0！');
            return;
        }
        let requestBody = {
            bookNumber: value
        };
        console.log(requestBody);
        let tmp = this.state.cart;
        for (let i = 0; i < tmp.length; ++i) {
            if (tmp[i].key === id) {
                tmp[i].bookNumber = value;
                tmp[i].sum = (tmp[i].bookPrice / 100 * value).toFixed(1);
            }
        }
        this.setState({cart: tmp, showCart: tmp});
        const callback = (response) => {
            message.success('修改成功');
        }
        editCartItemNumber(id, requestBody, callback);
    }

    submitOrder = () => {

        if (this.state.selectedRowKeys.length === 0) {
            message.error("please choose at least one item");
            return;
        }

        let items = [];
        const data = this.state.selectedRows;
        for (let i = 0; i < data.length; i++) {
            items.push({
                cartItemId: data[i].key,
                bookId: data[i].bookId,
                bookNumber: data[i].bookNumber,
            });
        }
        console.log(items);
        let requestBody = {
            orderItemList: items
        };

        const callback = (response) => {
            this.fetchCarts(this.state.page, true, message.success("请至订单界面查询订单信息"));
        }
        console.log(requestBody);
        addOrder(requestBody, callback);
    };

    render() {
        const {loading, selectedRowKeys, selectedRows} = this.state;
        const rowSelection = {
            onChange: (selectedRowKeys, selectedRows) => {
                console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
                this.setState({selectedRowKeys: selectedRowKeys, selectedRows: selectedRows})
            },
        };

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

        const hasSelected = this.calItems(selectedRowKeys, selectedRows) > 0;

        return (
            <div className="container">
                <br/>
                <Search value={this.state.searchValue} placeholder="search for cartItem" onChange={this.searchChange}
                        enterButton/>
                <br/>
                <br/>
                <Table
                    rowSelection={rowSelection}
                    columns={columns}
                    dataSource={this.state.showCart}
                    pagination={{
                        position: "bottom",
                        current: this.state.page,
                        pageSize: this.state.pageSize,
                        total: this.state.totalCartNumber,
                        onChange: this.fetchCarts
                    }}
                />
                <div style={{marginBottom: 16}}>
                    <Button type="primary" onClick={this.submitOrder} loading={loading}>
                        Submit
                    </Button>
                    <span style={{marginLeft: 8, color: "#e8e8e8"}}>
                        {hasSelected ? `${this.calItems(selectedRowKeys, selectedRows)} items` : ''}
                    </span>
                    <span style={{marginLeft: 10, color: "#e8e8e8"}}>
                        {hasSelected ? `Total: ￥${this.totalSum(selectedRowKeys).toFixed(1)} ` : ''}
                    </span>
                </div>
            </div>
        )
    }
}