/**
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.windowsxp.bookstore.websocket.messages;

import java.util.List;

/* Represents the list of users currently connected to the chat */
public class UsersMessage extends Message {
    private final List<String> userList;
    
    public UsersMessage(List<String> userList) {
        this.userList = userList;
    }
    
    public List<String> getUserList() {
        return userList;
    }
    
    /* For logging purposes */
    @Override
    public String toString() {
        return "[UsersMessage] " + userList.toString();
    }
}
