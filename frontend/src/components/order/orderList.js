import React from 'react';
import {DatePicker, Input, List, message} from 'antd'
import OrderDetail from "./orderDetail";
import {getOrderNumber, getOrders} from "../../services/orderService";
import {getBooks} from "../../services/bookService";

const {Search} = Input;

const {RangePicker} = DatePicker;

export class OrderList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            orders: [],
            showOrders: [],
            searchValue: '',
            page: 1,
            pageSize: 10,
            totalOrderNumber: 0
        };
    }

    fetchOrders = (page) => {
        const callback = (response) => {
            this.setState({
                orders: response.data.content,
                showOrders: response.data.content,
            });
        };
        this.setState({
            page: page
        });
        getBooks({page: page - 1, pageSize: this.state.pageSize}, callback);
    }

    componentDidMount() {

        const getOrderCallback = (response) => {
            console.log(response);
            this.setState({
                orders: response.data.content,
                showOrders: response.data.content
            });
        };
        getOrders({page: this.state.page - 1, pageSize: this.state.pageSize}, getOrderCallback);

        const getOrderNumberCallback = (response) => {
            this.setState({totalOrderNumber: response.data});
        }
        getOrderNumber(getOrderNumberCallback);
    }

    searchChange = ({target: {value}}) => {
        this.setState({searchValue: value})
        let arr = [];
        let list = this.state.orders;
        let search = value.toLowerCase();

        for (let i = 0; i < list.length; i++) {
            if (
                list[i].orderId.toString() === search.toString()
            ) {
                arr.push(list[i]);
            }
            for (let j = 0; j < list[i].orderItem.length; j++) {
                if (
                    list[i].orderItem[j].book.name.toLowerCase().indexOf(search) >= 0
                ) {
                    arr.push(list[i]);
                }
            }
        }
        this.setState(
            {showOrders: arr}
        );
    }

    timeChange = (value, dateString) => {
        if (dateString[0] === '' || dateString[1] === '') {
            this.setState(
                {showOrders: this.state.orders}
            );
            return;
        }
        console.log('Formatted Selected Time: ', dateString);
        const startTime = new Date(Date.parse(dateString[0]));
        const endTime = new Date(Date.parse(dateString[1]));
        let arr = [];
        let list = this.state.showOrders;
        for (let i = 0; i < list.length; i++) {
            let time = new Date(Date.parse(list[i].time));
            if (
                time > startTime && time < endTime
            ) {
                arr.push(list[i]);
            }
        }
        this.setState(
            {showOrders: arr}
        );
    }

    render() {
        return (
            <div>
                <br/>
                <br/>
                <Search value={this.state.searchValue} placeholder="search for orders" onChange={this.searchChange}
                        enterButton/>
                <br/>
                <br/>
                <RangePicker onChange={this.timeChange}/>
                <br/>
                <br/>
                <List
                    dataSource={this.state.showOrders}
                    pagination={{
                        position: "bottom",
                        current: this.state.page,
                        pageSize: this.state.pageSize,
                        total: this.state.totalOrderNumber,
                        onChange: this.fetchOrders
                    }}
                    renderItem={item => (
                        <List.Item>
                            <List.Item.Meta
                                title={`order ID is: ${item.id}`}
                                description={`order Time is: ${item.time}`}
                            />
                            <OrderDetail info={item.orderItem}/>
                        </List.Item>
                    )}
                />
            </div>
        );
    }
}
