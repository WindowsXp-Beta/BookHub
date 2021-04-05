import React from 'react';
import {Layout, Carousel} from 'antd'
import {HeaderInfo} from "../components/Layout/HeaderInfo";
import {SideBar} from "../components/Layout/SideBar";
import '../css/bookDetail.css'
import {withRouter} from "react-router-dom";


//import {getBook} from "../services/bookService";
import {BookDetail} from "../components/Book/BookDetail";

const { Header, Content, Footer } = Layout;

const data = {
        name: "Computer systems: A Programmer's Perspective",
        author: "Randal E. Bryant • David R. O’Hallaron",
        type: "理工类/计算机",
        price: 130,
        inventory: 1,
        description: "Computer systems: A Programmer’s Perspective explains the underlying elements common among all computer systems and how they affect general application performance. Written from the programmer’s perspective, this book strives to teach readers how understanding basic elements of computer systems and executing real practice can lead them to create better programs.",
};
class BookView extends React.Component{

    constructor(props) {
        super(props);

        this.state = {
            bookInfo: data,
        };



    }

    // componentDidMount(){
    //     let user = localStorage.getItem("user");
    //     this.setState({user:user});
    //
    //     const query = this.props.location.search;
    //     const arr = query.split('&');
    //     const bookId = arr[0].substr(4);
    //     getBook(bookId, (data) => {this.setState({bookInfo: data})})
    // }

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
                            <BookDetail info={this.state.bookInfo} />

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