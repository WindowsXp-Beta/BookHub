import React from 'react';
import {Layout} from "antd";
import {HeaderInfo} from "../components/Layout/HeaderInfo";
import {SideBar} from "../components/Layout/SideBar";
import {CartList} from "../components/Cart/cartList";

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