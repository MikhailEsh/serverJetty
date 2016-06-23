package game;

import base.HandlerIdSession;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import base.GameMechanics;
import base.WebSocketService;
import javax.servlet.annotation.WebServlet;

/**
 * Created by Агент on 07.09.15.
 */

@WebServlet(name = "WebSocketGameServlet", urlPatterns = {"/gameplay"})
public class WebSocketGameServlet extends WebSocketServlet {
    private final static int IDLE_TIME = 60 * 1000;
    private HandlerIdSession handlerIdSession;
    private GameMechanics gameMechanics;
    private WebSocketService webSocketService;

    public WebSocketGameServlet(HandlerIdSession handlerIdSession,
                                GameMechanics gameMechanics,
                                WebSocketService webSocketService) {
        this.handlerIdSession = handlerIdSession;
        this.gameMechanics = gameMechanics;
        this.webSocketService = webSocketService;
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(IDLE_TIME);
        factory.setCreator(new GameWebSocketCreator(handlerIdSession, gameMechanics, webSocketService));
    }
}
