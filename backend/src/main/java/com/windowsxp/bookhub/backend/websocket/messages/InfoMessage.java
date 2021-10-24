package com.windowsxp.bookhub.backend.websocket.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

/* Represents an information message, like
 * an user entering or leaving the chat */
@AllArgsConstructor
@Getter
public class InfoMessage extends Message {
    
    private final String info;
    
    /* For logging purposes */
    @Override
    public String toString() {
        return "[InfoMessage] " + info;
    }
}
