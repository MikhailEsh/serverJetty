package chat;

/**
 * Created by Агент on 04.09.15.
 */


import base.HandlerIdSession;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatWebSocketCreator implements WebSocketCreator{
    private Set<ChatWebSocket> users;
    private HandlerIdSession handlerIdSession;

    public ChatWebSocketCreator(HandlerIdSession handlerIdSession) {
        this.handlerIdSession = handlerIdSession;
        this.users = Collections.newSetFromMap(new ConcurrentHashMap<ChatWebSocket, Boolean>());
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        String sessionId = req.getHttpServletRequest().getSession().getId();
        ChatWebSocket socket = new ChatWebSocket(users, this.handlerIdSession, sessionId);
        return socket;
    }
}
