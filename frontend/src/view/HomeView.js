import React from 'react';
import {Layout, BackTop} from 'antd'
import {HeaderInfo} from "../components/Layout/HeaderInfo";
import {SideBar} from "../components/Layout/SideBar";
import '../css/home.css';
import {withRouter} from "react-router-dom";
import {BookCarousel} from "../components/Book/BookCarousel";
import {SearchBar} from "../components/Layout/SearchBar";
import {BookList} from "../components/Book/BookList";

const { Header, Content} = Layout;

class HomeView extends React.Component{

    constructor(props) {
        super(props);
        // console.log(props.location.state.name);
    }

    componentDidMount(){
        let user = localStorage.getItem("user");
        this.setState({user:user});
    }

    render(){
        return(
            <Layout className="layout">
                <Header>
                    <HeaderInfo />
                </Header>
                <Layout>
                    <SideBar key='1'/>
                    <Content style={{ padding: '0 50px' }}>
                        <div className="home-content">
                            {/*<SearchBar />*/}

                            <BookCarousel />
                            <BookList />
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

export default withRouter(HomeView);