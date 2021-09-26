package com.windowsxp.bookstore.websocket.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/* Represents a chat message */
public class ChatMessage extends Message {
    private Integer target;
    private String content;

    /* For logging purposes */
    @Override
    public String toString() {
        return String.format("[ChatMessage] to %s %s" ,target, content);
    }
}
