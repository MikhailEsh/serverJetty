package game;


import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

import base.HandlerIdSession;
import base.GameMechanics;
import base.WebSocketService;


/**
 * Created by Агент on 07.09.15.
 */
public class GameWebSocketCreator implements WebSocketCreator {
    private HandlerIdSession handlerIdSession;
    private GameMechanics gameMechanics;
    private WebSocketService webSocketService;

    public GameWebSocketCreator(HandlerIdSession handlerIdSession,
                                GameMechanics gameMechanics,
                                WebSocketService webSocketService) {
        this.handlerIdSession = handlerIdSession;
        this.gameMechanics = gameMechanics;
        this.webSocketService = webSocketService;
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        String sessionId = req.getHttpServletRequest().getSession().getId();
        String name = handlerIdSession.getNameBySessionId(sessionId);
        return new GameWebSocket(name, gameMechanics, webSocketService);
    }
}
