import React from 'react';
import {Layout} from "antd";
import {HeaderInfo} from "../components/layout/headerInfo";
import {SideBar} from "../components/layout/sideBar";
import {CartList} from "../components/cart/cartList";
import '../css/cart.css'

const { Header, Content} = Layout;

class CartView extends React.Component {
    render()    {
        return(
            <Layout className="layout">
                <Header>
                    <HeaderInfo/>
                </Header>
                <Layout>
                    <SideBar key='2'/>
                    <Content style={{ padding: '0 50px' }}>
                        <div className="home-content">
                            <CartList />
                            <div className={"foot-wrapper"}>
                            </div>
                        </div>
                    </Content>
                </Layout>
            </Layout>
        );
    }
}

export default CartView;