import React from 'react';
import {Layout, BackTop, Menu} from 'antd'
import {HeaderInfo} from "../components/layout/headerInfo";
import {SideBar} from "../components/layout/sideBar";
import '../css/home.css';
import {withRouter} from "react-router-dom";
import {BookCarousel} from "../components/book/bookCarousel";
import {BookList} from "../components/book/bookList";
import {history} from '../utils/history';

const {Header, Content, Footer} = Layout;

class HomeView extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            user: {},
            key: '',
            pageView: 0
        };
    }

    componentDidMount() {
        let user = localStorage.getItem("user");
        console.log(user);
        this.setState({
            user: user,
        });
    }

    setPageView = (pageView) => {
        this.setState({
            pageView: pageView
        });
    }

    render() {
        return (
            <Layout className="layout">
                <Header>
                    <HeaderInfo pageView={this.state.pageView}/>
                </Header>
                <Layout>
                    <SideBar key='1'/>
                    <div className="home-content">
                        <BookCarousel/>
                        <BookList updatePageView={this.setPageView}/>
                    </div>
                    <BackTop/>
                </Layout>
            </Layout>
        );
    }
}

export default withRouter(HomeView);