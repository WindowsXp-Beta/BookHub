import React, { useState }  from 'react';
import Chat, { Bubble, useMessages } from '@chatui/core';
import '@chatui/core/dist/index.css';

const initialMessages = [
    {
        type: 'text',
        content: { text: '您好，我是Book Hub的客服，您有什么问题吗~' },
        user: { avatar: '//gw.alicdn.com/tfs/TB1DYHLwMHqK1RjSZFEXXcGMXXa-56-62.svg' },
    },
];

// 默认快捷短语，可选
const defaultQuickReplies = [
    {
        icon: 'message',
        name: '联系人工服务',
        isNew: true,
        isHighlight: true,
    },
    {
        name: '短语1',
        isNew: true,
    },
    {
        name: '短语2',
        isHighlight: true,
    },
    {
        name: '短语3',
    },
];

export function ChatContainer(props) {
    // 消息列表
    const { messages, appendMsg, setTyping } = useMessages(initialMessages);
    const webSocket = props.WebSocket;
    const userId = props.userId;
    // 发送回调
    function handleSend(type, val) {
        if (type === 'text' && val.trim()) {
            console.log(val);
            let chatMessage = {
                type: "chat",
                name: "wxp",
                target: `${userId}`,
                message: val
            }
            webSocket.send(JSON.stringify(chatMessage));
            appendMsg({
                type: 'text',
                content: { text: val },
                position: 'right',
            });

            setTyping(true);

            // 模拟回复消息
            webSocket.onmessage = (event) => {
                let data = event.data;
                console.log(data);
                appendMsg({
                    type: 'text',
                    content: { text: data },
                });
            }
        }
    }

    // 快捷短语回调，可根据 item 数据做出不同的操作，这里以发送文本消息为例
    function handleQuickReplyClick(item) {
        handleSend('text', item.name);
    }

    function renderMessageContent(msg) {
        const { type, content } = msg;

        // 根据消息类型来渲染
        switch (type) {
            case 'text':
                return <Bubble content={content.text} />;
            case 'image':
                return (
                    <Bubble type="image">
                        <img src={content.picUrl} alt="" />
                    </Bubble>
                );
            default:
                return null;
        }
    }

    return (
        <Chat
            navbar={{ title: 'BookHub Chat Room' }}
            messages={messages}
            renderMessageContent={renderMessageContent}
            quickReplies={defaultQuickReplies}
            onQuickReplyClick={handleQuickReplyClick}
            onSend={handleSend}
        />
    );
}
