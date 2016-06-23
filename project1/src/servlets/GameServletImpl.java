package servlets;

import base.AccauntService;
import base.HandlerIdSession;
import main.UserProfile;
import templates.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Агент on 07.09.15.
 */
public class GameServletImpl extends CommonServlet {
    public static String path = "/gamePage";

    public GameServletImpl( HandlerIdSession handlerIdSession ) {
        super( handlerIdSession );
        srcHtml = "server_html";
        pageDefault = "gamePage.html";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionId = handlerIdSession.getSessionId( request );
        Map<String, Object> pageVariables = new HashMap<>();
        boolean singIn = handlerIdSession.checkInput(sessionId);
        setPageVariables(singIn, pageVariables, sessionId);
        setResponse(pageVariables, response);
    }
}
