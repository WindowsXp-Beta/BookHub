package com.windowsxp.bookstore.websocket.encoders;

import com.windowsxp.bookstore.websocket.messages.JoinMessage;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;

/* Encode a JoinMessage as JSON.
 * For example, (new JoinMessage("Peter"))
 * is encoded as follows:
 * {
 *   "type": "join",
 *   "name": "Peter"
 * }
 */
public class JoinMessageEncoder implements Encoder.Text<JoinMessage> {
    @Override
    public void init(EndpointConfig ec) { }
    
    @Override
    public void destroy() { }
    
    @Override
    public String encode(JoinMessage joinMessage) throws EncodeException {
        StringWriter stringWriter = new StringWriter();
        try (JsonGenerator jsonGen = Json.createGenerator(stringWriter)) {
            jsonGen.writeStartObject()
                .write("type", "join")
                .write("name", joinMessage.getName())
            .writeEnd();
        }
        return stringWriter.toString();
    }
}
