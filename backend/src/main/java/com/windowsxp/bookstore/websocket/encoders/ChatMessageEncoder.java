package com.windowsxp.bookstore.websocket.encoders;

import com.windowsxp.bookstore.websocket.messages.ChatMessage;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;


/* Encode a ChatMessage as JSON.
 * For example, (new ChatMessage("Peter","Duke","How are you?"))
 * is encoded as follows:
 * {
 *   "type": "chat",
 *   "target": "Duke",
 *   "message": "How are you?"
 * }
 */
public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {
    
    @Override
    public void init(EndpointConfig ec) { }
    
    @Override
    public void destroy() { }
    
    @Override
    public String encode(ChatMessage chatMessage) throws EncodeException {
        StringWriter stringWriter = new StringWriter();
        try (JsonGenerator jsonGenerator = Json.createGenerator(stringWriter)) {
            jsonGenerator.writeStartObject()
                .write("name", chatMessage.getName())
                .write("message", chatMessage.getMessage())
            .writeEnd();
        }
        return stringWriter.toString();
    }
}
