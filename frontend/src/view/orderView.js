import React from 'react';
import {Layout,BackTop} from 'antd'
import {HeaderInfo} from "../components/Layout/headerInfo";
import {SideBar} from "../components/Layout/sideBar";
import {OrderList} from "../components/Order/orderList";
import "../css/order.css"
const { Header, Content} = Layout;
class OrderView extends React.Component {

    render()    {
        return(
            <Layout className="layout">
                <Header>
                    <HeaderInfo/>
                </Header>
                <Layout>
                    <SideBar key='3'/>
                    <Content style={{ padding: '0 50px' }}>
                        <div className="home-content">
                            <OrderList />
                            <div className={"foot-wrapper"}>
                            </div>
                        </div>
                        <BackTop />
                    </Content>
                </Layout>
            </Layout>
        );
    }
}
export default OrderView;