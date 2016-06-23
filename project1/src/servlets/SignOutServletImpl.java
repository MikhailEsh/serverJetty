package servlets;


import base.AccauntService;
import base.HandlerIdSession;
import servlets.CommonServlet;
import templates.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Агент on 04.09.15.
 */
public class SignOutServletImpl extends CommonServlet {

    public static String path = "/api/v1/auth/signout";

    public SignOutServletImpl( HandlerIdSession handlerIdSession) {
        super(handlerIdSession);
        srcHtml = "static";
        pageDefault = "index.html";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionId = handlerIdSession.getSessionId( request );
        Map<String, Object> pageVariables = new HashMap<>();
        handlerIdSession.removeSessionId(sessionId);
        boolean singIn = false;
        setPageVariables(singIn, pageVariables, sessionId);
        setResponse(pageVariables, response);
    }
}
