import React from 'react';
import {DatePicker,Input, List, message} from 'antd'
import OrderDetail from "./OrderDetail";
import * as userService from '../../services/userService'

const {Search} = Input;

const { RangePicker } = DatePicker;


        // listId: "3",
        // time: "2021-04-01",
        // items: [
        //     {
        //         book: {
        //             title: "Algorithm",
        //         },
        //         bookNumber: "3",
        //         bookPrice: "80",
        //         sum: "240",
        //     }
        // ],

export class OrderList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            orders: [],
            showOrders: [],
            searchValue: '',
        };
    }

    componentDidMount() {

        const callback = (data) => {
            let tmp =[];
            for(let i = 0; i < data.length; i++) {
                let flag = false;
                for (let j = 0; j < tmp.length; j++) {
                    if (tmp[j].orderId === data[i].orderId) {
                        tmp[j].itemList.push(data[i].orderItem);
                        flag = true;
                        break;
                    }
                }
                if(flag) continue;
                tmp.push({
                        orderId:data[i].orderId,
                        itemList:[data[i].orderItem],
                        time:data[i].time
                    }
                );
            }
            this.setState({orders: tmp, showOrders: tmp});
        };

        let user = JSON.parse(localStorage.getItem('user'));

        if (user === null) {
            message.error("请登录");
        } else {
            let userId = user.userId;
            userService.getOrders(userId, callback);
        }

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
            for (let j = 0; j < list[i].itemList.length; j++) {
                if (
                    list[i].itemList[j].book.name.toLowerCase().indexOf(search) >= 0
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
        if(dateString[0]===''||dateString[1]==='')
        {
            this.setState(
                {showOrders: this.state.orders}
            );
            return;
        }
        console.log('Formatted Selected Time: ', dateString);
        const startTime= new Date(Date.parse(dateString[0]));
        const endTime=new Date(Date.parse(dateString[1]));
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
                <RangePicker onChange ={this.timeChange}/>
                <br/>
                <br/>
                <List
                    dataSource={this.state.showOrders}
                    renderItem={item => (
                        <List.Item >
                            <List.Item.Meta
                                title={'order:' + item.orderId}
                                description={'time:' + item.time}
                            />
                            <OrderDetail info={item.itemList}/>
                        </List.Item>
                    )}
                />
            </div>
        );
    }
}
