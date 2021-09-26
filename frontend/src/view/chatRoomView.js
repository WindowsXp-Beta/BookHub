import React from 'react';
import {Layout, BackTop, Tabs} from 'antd';
import {HeaderInfo} from "../components/Layout/headerInfo";
import {SideBar} from "../components/Layout/sideBar";
import {ChatContainer} from "../components/Chat/chatContainer";
import '../css/chatRoom.css'
import {ChatSideBar} from "../components/Chat/chatSideBar";

const {Header} = Layout;
const {TabPane} = Tabs;

class ChatRoomView extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            webSocket: null,
            admin: false,
            chatInfoList: []
        }
    }

    componentDidMount() {
        const user = JSON.parse(localStorage.getItem("user"));
        console.log(user);
        if (user && user.userType === 'ADMIN') {
            this.setState({admin: true, webSocket: new WebSocket(`ws://localhost:8080/chatRoom?auth=admin&userId=${user.userId}`)}, () => {
                this.state.webSocket.addEventListener("message", (event) => {
                    let message = JSON.parse(event.data);
                    if (message.type === "newChat") {
                        this.setState({chatInfoList: [...this.state.chatInfoList, message.chatInfo]});
                    }
                });
            });
        }
        else {
            this.setState({webSocket: new WebSocket("ws://localhost:8080/chatRoom?auth=user&userId=$(user.userId)")})
        }
    }


    render() {
        return (
            <Layout className="layout">
                <Header>
                    <HeaderInfo/>
                </Header>
                <Layout>
                    <SideBar key='2'/>
                    <div className="home-content">
                        {this.state.admin === false ? <ChatContainer WebSocket={this.state.webSocket} userId={1}/> :
                            <Tabs tabPosition={'right'}>
                                {this.state.chatInfoList.map((item) => {
                                    return (<TabPane tab={item.username} key="1">
                                        <ChatContainer WebSocket={this.state.webSocket} userId={item.userId}/>
                                    </TabPane>);
                                })}
                            </Tabs>}
                    </div>
                    <BackTop/>
                </Layout>
            </Layout>
        );
    }
}

export default ChatRoomView;