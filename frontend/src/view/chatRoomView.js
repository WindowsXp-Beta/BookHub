import React from 'react';
import {BackTop, Layout} from 'antd';
import {HeaderInfo} from "../components/layout/headerInfo";
import {SideBar} from "../components/layout/sideBar";
import {ChatContainer} from "../components/chat/chatContainer";

const {Header} = Layout;

class ChatRoomView extends React.Component {

    constructor(props) {
        super(props);
        const user = JSON.parse(localStorage.getItem("user"));
        console.log(user);
        this.state = {
            username: user.username,
            webSocket: new WebSocket(`ws://localhost:8080/chatroom?${user.username}`)
        }
    }


    render() {
        return (
            <Layout className="layout">
                <Header>
                    <HeaderInfo/>
                </Header>
                <Layout>
                    <SideBar key='9'/>
                    <div className="home-content">
                        <ChatContainer WebSocket={this.state.webSocket} username={this.state.username}/>
                    </div>
                    <BackTop/>
                </Layout>
            </Layout>
        );
    }
}

export default ChatRoomView;