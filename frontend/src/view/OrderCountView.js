import React from 'react';
import {OrderCountTable} from "../components/Order/OrderCountTable";
import {BackTop, Layout} from "antd";
import {HeaderInfo} from "../components/Layout/HeaderInfo";
import {SideBar} from "../components/Layout/SideBar";

const { Header, Content } = Layout;
class BestSalesView extends React.Component {
    render() {
        return (
            <Layout className="layout">
                <Header>
                    <HeaderInfo/>
                </Header>
                <Layout>
                    <SideBar />
                    <Content style={{ padding: '0 50px' }}>
                        <div className="home-content">
                            <OrderCountTable/>
                        </div>
                        <BackTop />
                    </Content>
                </Layout>
            </Layout>
        );
    }
}

export default BestSalesView;
