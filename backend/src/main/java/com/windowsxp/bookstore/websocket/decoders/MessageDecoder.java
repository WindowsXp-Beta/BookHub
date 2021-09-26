package com.windowsxp.bookstore.websocket.decoders;

import com.windowsxp.bookstore.utils.LogUtil;
import com.windowsxp.bookstore.websocket.messages.ChatMessage;
import com.windowsxp.bookstore.websocket.messages.JoinMessage;
import com.windowsxp.bookstore.websocket.messages.Message;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* Decode a JSON message into a JoinMessage or a ChatMessage.
 * For example, the incoming message:
 * {"type":"chat","name":"Peter","target":"Duke","message":"How are you?"}
 * is decoded as (new ChatMessage("Peter", "Duke", "How are you?"))
 */
public class MessageDecoder implements Decoder.Text<Message> {
    /* Stores the name-value pairs from a JSON message as a Map */
    private Map<String, String> messageMap;

    @Override
    public void init(EndpointConfig ec) {
    }

    @Override
    public void destroy() {
    }

    /* Create a new Message object if the message can be decoded */
    @Override
    public Message decode(String string) throws DecodeException {
        Message message = null;
        if (willDecode(string)) {
            message = new ChatMessage(Integer.valueOf(messageMap.get("target")), messageMap.get("content"));
        } else {
            throw new DecodeException(string, "[Message] Can't decode.");
        }
        return message;
    }

    /* Decode a JSON message into a Map and check if it contains
     * all the required fields according to its type. */
    @Override
    public boolean willDecode(String string) {
        LogUtil.debug(string);
        /* Convert the message into a map */
        messageMap = new HashMap<>();
        JsonParser parser = Json.createParser(new StringReader(string));
        while (parser.hasNext()) {
            if (parser.next() == JsonParser.Event.KEY_NAME) {
                String key = parser.getString();
                parser.next();
                String value = parser.getString();
                messageMap.put(key, value);
            }
        }
        /* Check the kind of message and if all fields are included */
        String[] chatMessageKeys = {"target", "content"};
        return messageMap.keySet().containsAll(Arrays.asList(chatMessageKeys));
    }
}
