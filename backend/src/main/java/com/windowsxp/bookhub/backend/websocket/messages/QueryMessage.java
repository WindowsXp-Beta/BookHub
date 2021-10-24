package com.windowsxp.bookhub.backend.websocket.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QueryMessage extends Message{
    private final String question;

    @Override
    public String toString() {
        return "[QueryMessage] " + question;
    }
}
