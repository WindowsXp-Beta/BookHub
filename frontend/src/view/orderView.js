import React from 'react';
import {BackTop, Layout} from 'antd'
import {HeaderInfo} from "../components/layout/headerInfo";
import {SideBar} from "../components/layout/sideBar";
import {OrderList} from "../components/order/orderList";
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