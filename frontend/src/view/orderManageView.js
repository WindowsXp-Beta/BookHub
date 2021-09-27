import React from 'react';
import {Layout} from 'antd'
import {HeaderInfo} from "../components/layout/headerInfo";
import {SideBar} from "../components/layout/sideBar";
import {OrderTable} from "../components/order/orderTable";

const {Header, Content} = Layout;

class OrderManageView extends React.Component {

    render() {
        return (
            <Layout className="layout">
                <Header>
                    <HeaderInfo/>
                </Header>
                <Layout>
                    <SideBar key={2}/>
                    <Content style={{padding: '0 50px'}}>
                        <div className="home-content">
                            <OrderTable/>
                            <div className={"foot-wrapper"}>
                            </div>
                        </div>
                    </Content>
                </Layout>
            </Layout>
        );
    }
}

export default OrderManageView;