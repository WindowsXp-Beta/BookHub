import React from 'react';
import {Layout, BackTop, Menu} from 'antd'
import {HeaderInfo} from "../components/Layout/headerInfo";
import {SideBar} from "../components/Layout/sideBar";
import '../css/home.css';
import {withRouter} from "react-router-dom";
import {BookCarousel} from "../components/Book/bookCarousel";
import {BookList} from "../components/Book/bookList";
import {history} from '../utils/history';

const {Header, Content, Footer} = Layout;

class HomeView extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            current: 1,
            user: {},
            key: ''
        };
    }

    componentWillMount() {
        let user = localStorage.getItem("user");
        console.log(user);
        this.setState({
            user: user,
        });
    }

    render() {
        return (
            <Layout className="layout">
                <Header>
                    <HeaderInfo/>
                </Header>
                <Layout>
                    <SideBar key='1'/>
                    <div className="home-content">
                        <BookCarousel/>
                        <BookList/>
                    </div>
                    <BackTop/>
                </Layout>
            </Layout>
        );
    }
}

export default withRouter(HomeView);