package com.windowsxp.bookstore.websocket.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

/* Represents a join message for the chat */
@AllArgsConstructor
@Getter
public class JoinMessage extends Message {
    private final String name;
    
    /* For logging purposes */
    @Override
    public String toString() {
        return "[JoinMessage] " + name;
    }
}
