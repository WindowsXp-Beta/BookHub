package com.windowsxp.bookstore.websocket.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/* Represents the list of users currently connected to the chat */
@AllArgsConstructor
@Getter
public class UsersMessage extends Message {
    private final List<String> userList;
    
    /* For logging purposes */
    @Override
    public String toString() {
        return "[UsersMessage] " + userList.toString();
    }
}
