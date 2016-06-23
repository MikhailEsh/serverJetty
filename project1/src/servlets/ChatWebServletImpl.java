package servlets;

import base.AccauntService;
import base.HandlerIdSession;
import servlets.CommonServlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Агент on 05.09.15.
 */
public class ChatWebServletImpl extends CommonServlet {

    public static String path = "/chatPage";

    public ChatWebServletImpl( HandlerIdSession handlerIdSession) {
        super( handlerIdSession );
        srcHtml = "server_html";
        pageDefault = "chatPage.html";
    }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String sessionId = handlerIdSession.getSessionId(request);
            Map<String, Object> pageVariables = new HashMap<>();
            boolean singIn = handlerIdSession.checkInput(sessionId);
            setPageVariables(singIn, pageVariables, sessionId);
            setResponse(pageVariables, response);
        }
}
