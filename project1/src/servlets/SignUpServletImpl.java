package servlets;

import base.*;
import main.UserProfile;
import templates.PageGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Агент on 08.08.15.
 */
public class SignUpServletImpl extends CommonServlet {

    public static String path = "/api/v1/auth/signup";

    private AccauntService accauntService;

    public SignUpServletImpl( AccauntService accauntService,  HandlerIdSession handlerIdSession) {
        super( handlerIdSession );
        this.accauntService = accauntService;
        srcHtml = "server_html";
        pageDefault = "signUp.html";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String sessionId = handlerIdSession.getSessionId(request);
        Map<String, Object> pageVariables = new HashMap<>();
        String name = request.getParameter("name");
        boolean singIn = handlerIdSession.checkInput(sessionId);
        boolean trySign = false;
        if ( name != null ) {
            UserProfile user = createUser(request);
            trySign = true;
            singIn = handlerSign(user, sessionId);
        }
        setPageVariables(singIn, pageVariables, sessionId);
        pageVariables.put("trySign", PageGenerator.wrapperBoolean(trySign));
        setResponse(pageVariables, response);
    }


    protected boolean handlerSign(UserProfile user, String sessionId) {
        if ( accauntService.addUser(user) ) {
            handlerIdSession.addSessionId(sessionId, user.getName());
            return true;
        } else return false;
    }

}
