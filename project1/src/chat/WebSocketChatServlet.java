package chat;

import base.HandlerIdSession;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;


import javax.servlet.annotation.WebServlet;


/**
 * Created by Агент on 04.09.15.
 */



@WebServlet(name = "WebSocketChatServlet", urlPatterns = {"/chat"})
public class WebSocketChatServlet extends WebSocketServlet{

    private HandlerIdSession handlerIdSession;

    public static String path = "/chat";
    private final static int LOGOUT_TIME = 10 * 60 * 1000;

    public WebSocketChatServlet(HandlerIdSession handlerIdSession) {
        this.handlerIdSession = handlerIdSession;
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator(new ChatWebSocketCreator( this.handlerIdSession ));
    }


}
