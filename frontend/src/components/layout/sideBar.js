import React from 'react';
import {Layout, Menu} from 'antd';
import "../../css/sider.css"
import {
    AimOutlined,
    EditOutlined,
    LineChartOutlined,
    MessageOutlined,
    ReadOutlined,
    ShoppingCartOutlined,
    SnippetsOutlined,
    SolutionOutlined,
    StarOutlined,
    UserOutlined
} from '@ant-design/icons';

import {Link} from "react-router-dom";

const { Sider } = Layout;// Sider = layout.Sider

export class SideBar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            collapsed: false,
            admin: false,
        };
    }

    componentDidMount() {
        const user = JSON.parse(localStorage.getItem("user"));
        if(user && user.userType === 0){
            this.setState({admin: true})
        }
    }

    onCollapse = collapsed => {
        console.log(collapsed);
        this.setState({ collapsed: collapsed });
    };


    render() {
        return (
            <div className="sider_back" style={{width:this.state.collapsed? "80px":"180px", maxWidth:this.state.collapsed? "80px":"180px", minWidth:this.state.collapsed? "80px":"180px" }}>
                <div className="mySider">
                    <Sider collapsible collapsed={this.state.collapsed} width="180px" onCollapse={this.onCollapse} className="sider" >
                        <Menu
                            selectedKeys={[this.props.key]}
                            defaultSelectedKeys={['1']}
                            mode="inline">
                            <Menu.Item key="1">
                                <Link to={"/"}>
                                <ReadOutlined  style={{ fontSize: '18px', color:'#fff'}}/>
                                <span style={{ fontSize: '16px', color: '#a2a2a2'}}>Books</span>
                                </Link>
                            </Menu.Item>
                            <Menu.Item key="2">
                                <Link to={"/CartView"}>
                                <ShoppingCartOutlined  style={{ fontSize: '18px', color: '#fff'}} />
                                <span style={{ fontSize: '16px', color: '#a2a2a2'}}>My Cart</span>
                                </Link>
                            </Menu.Item>
                            <Menu.Item key="3">
                                <Link to={"/OrderView"}>
                                <SolutionOutlined  style={{ fontSize: '18px', color: '#fff'}}/>
                                <span style={{ fontSize: '16px', color: '#a2a2a2'}}>My Orders</span>
                                </Link>
                            </Menu.Item>
                            <Menu.Item key="4">
                                <Link to={"/OrderCountView"}>
                                <LineChartOutlined style={{ fontSize: '18px', color: '#fff'}}/>
                                <span style={{ fontSize: '16px', color: '#a2a2a2'}}>Order Statistic</span>
                                </Link>
                            </Menu.Item>
                            {(this.state.admin)?
                                (
                                    <Menu.Item key="5">
                                        <Link to='/orderManage'>
                                            <SnippetsOutlined />
                                            <span style={{fontSize: '16px', color: '#a2a2a2'}}>Order Manage</span>
                                        </Link>
                                    </Menu.Item>
                                ):
                                (<div/>)
                            }
                            {(this.state.admin)?
                                (
                                    <Menu.Item key="6">
                                        <Link to='/bookManage'>
                                            <EditOutlined/>
                                            <span style={{fontSize: '16px', color: '#a2a2a2'}}>Book Manage</span>
                                        </Link>
                                    </Menu.Item>
                                ):
                                (<div/>)
                            }
                            {(this.state.admin)?
                                (
                                    <Menu.Item key="7">
                                        <Link to='/userManage'>
                                            <UserOutlined/>
                                            <span style={{fontSize: '16px', color: '#a2a2a2'}}>User Manage</span>
                                        </Link>
                                    </Menu.Item>
                                ):
                                (<div/>)
                            }
                            {(this.state.admin)?
                                (
                                    <Menu.Item key="8">
                                        <Link to='/BestSales'>
                                            <StarOutlined/>
                                            <span style={{fontSize: '16px', color: '#a2a2a2'}}>Best Sales</span>
                                        </Link>
                                    </Menu.Item>
                                ):
                                (<div/>)
                            }
                            {(this.state.admin)?
                                (
                                    <Menu.Item key="9">
                                        <Link to='/BestCustomer'>
                                            <AimOutlined/>
                                            <span style={{fontSize: '16px', color: '#a2a2a2'}}>Best Customers</span>
                                        </Link>
                                    </Menu.Item>
                                ):
                                (<div/>)
                            }
                            <Menu.Item key="10">
                                <Link to={"/chatRoomView"}>
                                    <MessageOutlined style={{ fontSize: '18px', color: '#fff'}}/>
                                    <span style={{ fontSize: '16px', color: '#a2a2a2'}}>Chat Room</span>
                                </Link>
                            </Menu.Item>
                        </Menu>
                    </Sider>
                </div>
            </div>

        );
    }
}
