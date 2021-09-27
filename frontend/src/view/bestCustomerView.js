import React from 'react';
import '../css/data.css';
import {BackTop, Layout} from "antd";
import {HeaderInfo} from "../components/layout/headerInfo";
import {SideBar} from "../components/layout/sideBar";
import {BestCustomerTable} from "../components/user/bestCustomerTable";

const { Header, Content} = Layout;
class BestCustomerView extends React.Component {
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
                            <BestCustomerTable/>
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

export default BestCustomerView;
