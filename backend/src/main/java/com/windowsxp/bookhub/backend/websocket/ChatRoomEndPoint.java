package com.windowsxp.bookhub.backend.websocket;

import com.windowsxp.bookhub.backend.websocket.decoders.MessageDecoder;
import com.windowsxp.bookhub.backend.websocket.encoders.ChatMessageEncoder;
import com.windowsxp.bookhub.backend.websocket.encoders.InfoMessageEncoder;
import com.windowsxp.bookhub.backend.websocket.encoders.JoinMessageEncoder;
import com.windowsxp.bookhub.backend.websocket.encoders.UsersMessageEncoder;
import com.windowsxp.bookhub.backend.websocket.messages.*;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Websocket endpoint */
@ServerEndpoint(
        value = "/chatroom",
        decoders = { MessageDecoder.class },
        encoders = { JoinMessageEncoder.class, ChatMessageEncoder.class,
                     InfoMessageEncoder.class, UsersMessageEncoder.class }
        )
@Component
/* There is a BotEndpoint instance per connection */
public class ChatRoomEndPoint {
    private static final Logger logger = Logger.getLogger("BotEndpoint");
    private static final Queue<Session> userSession = new ConcurrentLinkedQueue<>();
    /* Bot functionality bean */
//    @Autowired
//    BotStockBean botStockBean;
    /* Executor service for asynchronous processing */
    
    @OnOpen
    public void openConnection(Session session) {
        userSession.add(session);
        String username = session.getQueryString();
        session.getUserProperties().put("username", username);
        sendAll(new InfoMessage(username + " has joined the chat"), session);
        logger.log(Level.INFO, String.format("Connection %s opened. %s has joined!", session.getId(), username));
    }
    
    @OnMessage
    public void message(final Session session, Message message) {
        logger.log(Level.INFO, "Received: {0}", message.toString());
        
        if (message instanceof QueryMessage) {
            /* Add the new user and notify everybody */
            QueryMessage queryMessage = (QueryMessage) message;
            sendToUser(session, botResponse(queryMessage));
        } else if (message instanceof ChatMessage) {
            /* Forward the message to everybody */
            final ChatMessage chatMessage = (ChatMessage) message;
            logger.log(Level.INFO, "Received: {0}", chatMessage.toString());
            sendAll(chatMessage, session);
        }
    }
    
    @OnClose
    public void closedConnection(Session session) {
        /* Notify everybody */
        String name = session.getUserProperties().get("username").toString();
        sendAll(new InfoMessage(name + " has left the chat"), session);
        logger.log(Level.INFO, String.format("Connection %s closed.", session.getId()));
    }
    
    @OnError
    public void error(Session session, Throwable throwable) {
        logger.log(Level.SEVERE, String.format("Connection %s error %s", session.getId(), throwable.toString()));
    }
    
    /* Forward a message to all connected clients
     * The endpoint figures what encoder to use based on the message type */
    public synchronized void sendAll(Object message, Session session) {
        try {
            for (Session s : userSession){//session.getOpenSessions()) {
                if (s.isOpen() && !s.equals(session)) {
                    s.getBasicRemote().sendObject(message);
                    logger.log(Level.INFO, "Sent: {0}", message.toString());
                }
            }
        } catch (IOException | EncodeException e) {
            logger.log(Level.INFO, e.toString());
        }
    }

    public synchronized void sendToUser(Session session, Object message) {
        try {
            if(session.isOpen()) {
                session.getBasicRemote().sendObject(message);
                logger.log(Level.INFO, String.format("Sent to %s %s", session.getId(), message.toString()));
            }
        } catch (IOException | EncodeException e) {
            logger.log(Level.INFO, e.toString());
        }
    }

    /* Returns the list of users from the properties of all open sessions */
    public List<String> getUserList() {
        List<String> users = new ArrayList<>();
        for (Session s : userSession){
            if (s.isOpen())
                users.add(s.getUserProperties().get("username").toString());
        }
        return users;
    }

    private Message botResponse(QueryMessage queryMessage) {
        switch (queryMessage.getQuestion()){
            case "当前在线用户":
                return new UsersMessage(getUserList());
//            case "我的订单":
            default:
                return new InfoMessage("555......智能客服还不支持这个问题，如希望其他用户回答，请@对方的用户名");
        }
    }
}
