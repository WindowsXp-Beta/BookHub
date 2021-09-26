/**
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 * <p>
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.windowsxp.bookstore.websocket;

import com.windowsxp.bookstore.utils.LogUtil;
import com.windowsxp.bookstore.websocket.decoders.MessageDecoder;
import com.windowsxp.bookstore.websocket.encoders.ChatMessageEncoder;
import com.windowsxp.bookstore.websocket.encoders.InfoMessageEncoder;
import com.windowsxp.bookstore.websocket.encoders.JoinMessageEncoder;
import com.windowsxp.bookstore.websocket.encoders.UsersMessageEncoder;
import com.windowsxp.bookstore.websocket.messages.*;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/* Websocket endpoint */
@ServerEndpoint(
        value = "/chatRoom",
        decoders = {MessageDecoder.class},
        encoders = {JoinMessageEncoder.class, ChatMessageEncoder.class,
                InfoMessageEncoder.class, UsersMessageEncoder.class}
)
@Component
/* There is a Endpoint instance per connection */
public class Endpoint {
    private static final Map<Integer, Session> userSessionMap = new ConcurrentHashMap<>();
    private static Session adminSession;
    /* Bot functionality bean */
//    @Inject
//    private StockBean stockBean = new StockBean();
//    /* Executor service for asynchronous processing */
//    @Resource(name="tomcatThreadPool")
//    private ManagedExecutorService mes;

    @OnOpen
    public void openConnection(Session session) {
        Map<String, List<String>> queryMap = session.getRequestParameterMap();
        if (queryMap.get("auth").get(0).equals("admin")) {
            adminSession = session;
            session.getUserProperties().put("auth", "admin");
        } else {
            session.getUserProperties().put("auth", "user");
            userSessionMap.put(Integer.valueOf(queryMap.get("userId").get(0)), session);
            if (adminSession.isOpen()) {

            }
        }
        LogUtil.debug(String.format("Connection %s %s opened.", queryMap.get("auth").get(0), session.getId()));
    }

    @OnMessage
    public void message(final Session session, Message message) {
        LogUtil.debug(String.format("Received: %s from %s", message.toString(), session.getId()));

        if (message instanceof ChatMessage) {
            /* Forward the message to everybody */
            final ChatMessage chatMessage = (ChatMessage) message;
            sendToClient(session, chatMessage);
        }
    }

    @OnClose
    public void closedConnection(Session session) {
        /* Notify everybody */
        LogUtil.debug(String.format("Connection %s closed.", session.getId()));
    }

    @OnError
    public void error(Session session, Throwable t) {
        LogUtil.error(String.format("Connection error %s", t.toString()));
    }

    /* Forward a message to all connected clients
     * The endpoint figures what encoder to use based on the message type */
    public synchronized void sendToClient(Session session, Object msg) {
        try {
            if (session.isOpen()) {
                session.getBasicRemote().sendObject(msg);
                LogUtil.debug(String.format("Sent: %s to session %s", msg.toString(), session.getId()));
            }
        } catch (IOException | EncodeException e) {
            LogUtil.error(e.toString());
        }
    }

    /* Returns the list of users from the properties of all open sessions */
    public List<String> getUserList(Session session) {
        List<String> users = new ArrayList<>();
        //users.add("Duke");
        for (Session s : userSessionMap) {//session.getOpenSessions()) {
            if (s.isOpen() && (boolean) s.getUserProperties().get("active"))
                users.add(s.getUserProperties().get("name").toString());
        }
        return users;
    }
}
