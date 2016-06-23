package chat;

import base.HandlerIdSession;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.util.Set;

/**
 * Created by Агент on 04.09.15.
 */

@WebSocket
public class ChatWebSocket {


        private Set<ChatWebSocket> users;
        private Session session;
        private HandlerIdSession handlerIdSession;
        private String sessionId;

        public ChatWebSocket(Set<ChatWebSocket> users, HandlerIdSession handlerIdSession, String sessionId) {
            this.users = users;
            this.handlerIdSession = handlerIdSession;
            this.sessionId = sessionId;
        }

        @OnWebSocketMessage
        public void onMessage(String data) {
            if ( !this.handlerIdSession.checkInput(this.sessionId) ) return;
            String message = handlerIdSession.getNameBySessionId( this.sessionId ) + data;
            for (ChatWebSocket user : users) {
                try {
                    user.getSession().getRemote().sendString(message);
                } catch (Exception e) {
                    System.out.print(e);
                }
            }
        }

        @OnWebSocketConnect
        public void onOpen(Session session) {
            if ( !handlerIdSession.checkInput( this.sessionId ) ) return;
            users.add(this);
            setSession(session);
        }

        public Session getSession() {
            return session;
        }

        public void setSession(Session session) {
            this.session = session;
        }

        @OnWebSocketClose
        public void onClose(int statusCode, String reason) {
            users.remove(this);
        }

}
