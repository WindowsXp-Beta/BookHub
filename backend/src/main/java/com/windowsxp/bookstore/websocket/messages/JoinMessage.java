/**
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.windowsxp.bookstore.websocket.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/* Represents a join message for the chat */
public class JoinMessage extends Message {    
    private final String username;
    private final Integer userId;

    /* For logging purposes */
    @Override
    public String toString() {
        return String.format("[JoinMessage] %s %s", username, userId);
    }
}
