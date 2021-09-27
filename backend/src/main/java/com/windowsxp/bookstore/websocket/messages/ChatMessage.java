package com.windowsxp.bookstore.websocket.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

/* Represents a chat message */
@AllArgsConstructor
@Getter
public class ChatMessage extends Message {
    private final String name;
    private final String target;
    private String message;
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    /* For logging purposes */
    @Override
    public String toString() {
        return "[ChatMessage] " + name + "-" + target + "-" + message;
    }
}
