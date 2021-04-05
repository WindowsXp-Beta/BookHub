/*
import React from 'react';
import { Menu,Layout } from 'antd';
import { ShoppingCartOutlined, SolutionOutlined, UserOutlined, ReadOutlined } from '@ant-design/icons';

import {history} from "../utils/history";


const { SubMenu } = Menu;
const { Sider } = Layout;// Sider = Layout.Sider

export class SideBar extends React.Component {

    state = {
        collapsed: false,
    };

    onCollapse = collapsed => {
        if(collapsed){

        }
        console.log(collapsed);
        this.setState({ collapsed });
    };

    bookOnClick = () => {
        history.push("/");
    };

    render() {
        return (
            <div style={{width:this.state.collapsed? "80px":"180px", maxWidth:this.state.collapsed? "80px":"180px", minWidth:this.state.collapsed? "80px":"180px" }}>
                <div className="mySider">
                    <Sider collapsible collapsed={this.state.collapsed} width="180px" onCollapse={this.onCollapse} className="sider" style={{ background: '#fff'}}>
                        <Menu defaultSelectedKeys={['1']} mode="inline">
                            <Menu.Item key="1" onClick={this.bookOnClick}>
                                <ReadOutlined  style={{ fontSize: '18px'}}/>
                                <span style={{ fontSize: '16px'}}>Books</span>
                            </Menu.Item>
                            <Menu.Item key="2">
                                <ShoppingCartOutlined  style={{ fontSize: '18px'}} />
                                <span style={{ fontSize: '16px'}}>My Cart</span>
                            </Menu.Item>
                            <Menu.Item key="3">
                                <SolutionOutlined  style={{ fontSize: '18px'}}/>
                                <span style={{ fontSize: '16px'}}>My Orders</span>
                            </Menu.Item>
                            <Menu.Item key="4">
                                <UserOutlined  style={{ fontSize: '18px'}}/>
                                <span style={{ fontSize: '16px'}}>My Profile</span>
                            </Menu.Item>
                        </Menu>
                    </Sider>
                </div>
            </div>

        );
    }

}
*/

import React from 'react'
import { Menu,Layout, Icon} from 'antd'
import {history} from "../../utils/history";
import {Link} from "react-router-dom";


const { SubMenu } = Menu;
const { Sider } = Layout;// Sider = Layout.Sider

export class SideBar extends React.Component {

    state = {
        collapsed: false,
    };

    onCollapse = collapsed => {
        if(collapsed){
        }
        console.log(collapsed);
        this.setState({ collapsed });
    };

    // bookOnClick = () => {
    //     history.push("/HomeView");
    // };
    //
    // cartOnClick = () => {
    //     history.push("/CartView");
    // };
    //
    // orderOnClick = () => {
    //     history.push("/OrderView");
    // };

    render() {
        return (
            <div className="sider_back" style={{width:this.state.collapsed? "80px":"180px", maxWidth:this.state.collapsed? "80px":"180px", minWidth:this.state.collapsed? "80px":"180px" }}>
                <div className="mySider">
                    <Sider collapsible collapsed={this.state.collapsed} width="180px" onCollapse={this.onCollapse} className="sider" style={{ background: ''}}>
                        <Menu
                            selectedKeys={[this.props.key]}
                            // defaultSelectedKeys={['1']}
                              mode="inline">
                            <Menu.Item key="1">
                                <Link to="/HomeView">
                                <Icon type="read" style={{ fontSize: '18px', color: '#fff'}}/>
                                <span style={{ fontSize: '16px', color: '#a2a2a2'}}>Books</span>
                                </Link>
                            </Menu.Item>
                            <Menu.Item key="2">
                                <Link to="/CartView">
                                <Icon type="shopping-cart" style={{ fontSize: '18px', color: '#fff'}} />
                                <span style={{ fontSize: '16px', color: '#a2a2a2'}}>My Cart</span>
                                </Link>
                            </Menu.Item>
                            <Menu.Item key="3">
                                <Link to="/OrderView">
                                <Icon type="solution"  style={{ fontSize: '18px', color: '#fff'}}/>
                                <span style={{ fontSize: '16px', color: '#a2a2a2'}}>My Orders</span>
                                </Link>
                            </Menu.Item>
                            <Menu.Item key="4">
                                <Icon type="user" style={{ fontSize: '18px', color: '#fff'}}/>
                                <span style={{ fontSize: '16px', color: '#a2a2a2'}}>My Profile</span>
                            </Menu.Item>
                        </Menu>
                    </Sider>
                </div>
            </div>

        );
    }

}
