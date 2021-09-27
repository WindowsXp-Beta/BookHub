import React from 'react';
import {BackTop, Layout, Menu} from 'antd'
import {HeaderInfo} from "../components/layout/headerInfo";
import {SideBar} from "../components/layout/sideBar";
import '../css/home.css';
import {withRouter} from "react-router-dom";
import {BookCarousel} from "../components/book/bookCarousel";
import {BookList} from "../components/book/bookList";
import {history} from '../utils/history';

const { Header, Content, Footer} = Layout;

class HomeView extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            current: 1,
            user: {},
            key: ''
        };
    }

    componentWillMount(){
        let user = localStorage.getItem("user");
        console.log(this.props.location.state);
        let key = '1';
        if(this.props.location.state) {
            key = this.props.location.state.key;
        }
        this.setState({
            user: user,
            key: key,
            current: parseInt(key)
        });
    }

    onPageChange = (e) => {
        this.setState({
            current: e.key,
        });
        history.push('/', {key: e.key});
        history.go();
    };

    render() {
        const { current } = this.state;
        return(
            <Layout className="layout">
                <Header>
                    <HeaderInfo />
                </Header>
                <Layout>
                    <SideBar key='1'/>
                    <Layout>
                        <Content style={{ padding: '0 50px' }}>
                            <div className="home-content">                                
                                <BookCarousel />
                                <BookList 
                                    range={this.state.key}
                                />
                            </div>
                            <BackTop />
                        </Content>
                        <Footer>
                            <Menu className={"pageTag"} onClick = {this.onPageChange} selectedKeys={[current]} mode="horizontal">
                                <Menu.Item key={1}>
                                    Page 1
                                </Menu.Item>
                                <Menu.Item key={2}>
                                    Page 2
                                </Menu.Item>
                                <Menu.Item key={3}>
                                    Page 3
                                </Menu.Item>
                                <Menu.Item key={4}>
                                    Page 4
                                </Menu.Item>
                            </Menu>
                        </Footer>
                    </Layout>
                </Layout>
            </Layout>
        );
    }
}

export default withRouter(HomeView);