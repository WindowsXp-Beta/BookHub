import React from 'react';
import {Layout} from 'antd'
import {HeaderInfo} from "../components/Layout/headerInfo";
import {SideBar} from "../components/Layout/sideBar";
import {OrderTable} from "../components/Order/orderTable";

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