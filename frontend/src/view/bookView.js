import React from 'react';
import {Layout} from 'antd'
import {HeaderInfo} from "../components/layout/headerInfo";
import {SideBar} from "../components/layout/sideBar";
import '../css/bookDetail.css'
import {withRouter} from "react-router-dom";


import {getBookDetail} from "../services/bookService";
import {BookDetail} from "../components/book/bookDetail";

const { Header, Content } = Layout;

class BookView extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            bookDetail: null,
        };
    }

    componentDidMount(){
        const query = this.props.location.search;
        const arr = query.split('&');
        const bookId = arr[0].substr(4);
        getBookDetail(bookId, (response) => {this.setState({bookDetail: response.data})})
    }

    render(){
        return(
            <Layout className="layout">

                <Header>
                    <HeaderInfo />
                </Header>
                <Layout>
                    <SideBar />
                    <Content style={{ padding: '0 50px' }}>
                        <div className="home-content">
                            <BookDetail detail={this.state.bookDetail} />

                            <div className={"foot-wrapper"}>
                            </div>
                        </div>
                    </Content>
                </Layout>
            </Layout>
        );
    }
}

export default withRouter(BookView);
