import React from 'react';
import {Comment, Layout, Menu} from 'antd';
import {Link} from "react-router-dom";

const { Sider } = Layout;

export class ChatSideBar extends React.Component {

    setChatItemList = (chatInfoList) => {
        let chatItemList = [];
        for (let i = 0; i < chatInfoList.length; i++) {
            let chatInfoItem = chatInfoList[i];
            chatItemList.push(
                <Menu.Item>
                    <Link to={{
                        pathname: "/ChatRoom",
                        search: `?userId=${chatInfoItem.userId}`
                    }}>
                        <Comment
                            avatar='https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png'
                            author={chatInfoItem.author}
                            content={chatInfoItem.content}
                        />
                    </Link>
                </Menu.Item>
            );
        }
        this.setState({chatItemList: chatItemList});
    }

    constructor(props) {
        super(props);
        this.state = {
            chatInfoList: props.chatInfoList,
            chatItemList: []
        }
    }

    render() {
        return (
            <Sider>
                <Menu
                    defaultSelectedKeys={['1']}
                    mode="inline"
                >
                    {this.state.chatItemList}
                </Menu>
            </Sider>
        );
    }
}