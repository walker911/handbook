package com.walker.manual.twoot.adapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walker.manual.twoot.*;
import org.java_websocket.WebSocket;

import java.io.IOException;
import java.util.Optional;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/18
 */
public class WebSocketEndPoint implements ReceiverEndPoint {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String CMD = "cmd";

    private final Twootr twootr;
    private final WebSocket webSocket;

    private SenderEndPoint senderEndPoint;

    public WebSocketEndPoint(Twootr twootr, WebSocket webSocket) {
        this.twootr = twootr;
        this.webSocket = webSocket;
    }

    @Override
    public void onTwoot(Twoot twoot) {
        webSocket.send(String.format("{\"cmd\": \"twoot\", \"user\": \"%s\", \"msg\": \"%s\"}", twoot.getSenderId(), twoot.getContent()));
    }

    void onMessage(final String message) throws IOException {
        JsonNode json = mapper.readTree(message);
        String cmd = json.get(CMD).asText();
        switch (cmd) {
            case "logon": {
                String username = json.get("username").asText();
                String password = json.get("password").asText();
                Optional<SenderEndPoint> endPoint = twootr.onLogon(username, password, this);
                if (!endPoint.isPresent()) {
                    senderEndPoint = null;
                    webSocket.close();
                } else {
                    this.senderEndPoint = endPoint.get();
                }
                break;
            }

            case "follow": {
                String username = json.get("username").asText();
                sendStatusUpdate(senderEndPoint.onFollow(username).toString());
                break;
            }

            case "sendTwoot": {
                String id = json.get("id").asText();
                String content = json.get("content").asText();
                sendPosition(senderEndPoint.onSendTwoot(id, content));
                break;
            }

            case "deleteTwoot": {
                String id = json.get("id").asText();
                sendStatusUpdate(senderEndPoint.onDeleteTwoot(id).toString());
                break;
            }
        }
    }

    private void sendPosition(final Position position) {
        webSocket.send(String.format("{\"cmd\": \"sent\", \"position\": \"%s\"}", position.getValue()));
    }

    private void sendStatusUpdate(final String status) {
        webSocket.send(String.format("{\"cmd\": \"statusUpdate\", \"status\": \"%s\"}", status));
    }
}
