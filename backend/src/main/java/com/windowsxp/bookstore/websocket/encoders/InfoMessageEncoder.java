/**
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 * <p>
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.windowsxp.bookstore.websocket.encoders;


import com.windowsxp.bookstore.websocket.messages.InfoMessage;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;

/* Encode an InfoMessage as JSON.
 * For example, (new InfoMessage("Peter has joined the chat."))
 * is encoded as follows:
 * {
 *   "type": "info",
 *   "info": "Peter has joined the chat"
 * }
 */
public class InfoMessageEncoder implements Encoder.Text<InfoMessage> {
    @Override
    public void init(EndpointConfig ec) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public String encode(InfoMessage joinMessage) throws EncodeException {
        StringWriter stringWriter = new StringWriter();
        try (JsonGenerator jsonGenerator = Json.createGenerator(stringWriter)) {
            jsonGenerator.writeStartObject()
                    .write("type", "info")
                    .write("info", joinMessage.getInfo())
                    .writeEnd();
        }
        return stringWriter.toString();
    }
}
