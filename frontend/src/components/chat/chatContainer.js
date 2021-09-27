import React from 'react';
import Chat, {Bubble, useMessages} from '@chatui/core';
import '@chatui/core/dist/index.css';

const initialMessages = [
    {
        type: 'text',
        content: {text: '您好，我是Book Hub的客服，欢迎来到Book Hub读书讨论会~'},
        user: {avatar: '//gw.alicdn.com/tfs/TB1DYHLwMHqK1RjSZFEXXcGMXXa-56-62.svg'},
    },
];

// 默认快捷短语，可选
const defaultQuickReplies = [
    {
        name: '当前在线用户',
        // isNew: true,
        isHighlight: true,
    },
    // {
    //     name: '短语1',
    //     isNew: true,
    // },
    // {
    //     name: '短语2',
    //     isHighlight: true,
    // },
    // {
    //     name: '短语3',
    // },
];

export function ChatContainer(props) {
    // 消息列表
    const {messages, appendMsg, setTyping} = useMessages(initialMessages);
    const webSocket = props.WebSocket;
    const username = props.username;
    webSocket.onmessage = (event) => {
        let response;
        let data = JSON.parse(event.data);
        console.log(data);
        switch(data.type) {
            case "info":
                response = `Book Hub客服: ${data.info}`;
                break;
            case "chat":
                response = `${data.name}: @${data.target} ${data.message}`;
                break;
            case "users":
                response = `当前活跃用户: ${data.userList}`;
                break;
        }
        appendMsg({
            type: 'text',
            content: {text: response},
        });
    }

    // 发送回调
    function handleSend(type, val) {
        if (type === 'text' && val.trim()) {
            console.log(val);
            let request;
            if (val.search('@') !== -1) {
                let messageArray = val.split(/@|\s/);
                let target = messageArray[1];
                let message = messageArray.slice(2).join('');
                console.log(messageArray);
                console.log(message);
                console.log(target);
                request = {
                    type: "chat",
                    target: target,
                    name: username,
                    message: message
                }
            } else {
                request = {
                    type: "query",
                    question: val
                }
            }

            console.log(request);
            webSocket.send(JSON.stringify(request));
            appendMsg({
                type: 'text',
                content: {text: val},
                position: 'right',
            });
        }
    }

    // 快捷短语回调，可根据 item 数据做出不同的操作，这里以发送文本消息为例
    function handleQuickReplyClick(item) {
        handleSend('text', item.name);
    }

    function renderMessageContent(msg) {
        const {type, content} = msg;

        // 根据消息类型来渲染
        switch (type) {
            case 'text':
                return <Bubble content={content.text}/>;
            case 'image':
                return (
                    <Bubble type="image">
                        <img src={content.picUrl} alt=""/>
                    </Bubble>
                );
            default:
                return null;
        }
    }

    return (
        <Chat
            navbar={{title: 'BookHub Chat Room'}}
            messages={messages}
            renderMessageContent={renderMessageContent}
            quickReplies={defaultQuickReplies}
            onQuickReplyClick={handleQuickReplyClick}
            onSend={handleSend}
        />
    );
}