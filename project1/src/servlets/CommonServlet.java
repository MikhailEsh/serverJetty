package servlets;

import base.AccauntService;
import base.HandlerIdSession;
import main.UserProfile;
import templates.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Агент on 04.09.15.
 */
public abstract class CommonServlet extends HttpServlet {

    protected String srcHtml;
    protected  String pageDefault;

    protected HandlerIdSession handlerIdSession;

    public CommonServlet( HandlerIdSession handlerIdSession) {
        super();
        this.handlerIdSession = handlerIdSession;
    }

    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    protected UserProfile createUser ( HttpServletRequest request ) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        UserProfile user = new UserProfile ( name, password, email);
        return user;
    }


    protected void setResponse(Map<String, Object> pageVariables, HttpServletResponse response) throws IOException{

        String streamString = PageGenerator.getPage( pageVariables, srcHtml, pageDefault );
        response.getWriter().println( streamString );
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected void setPageVariables(boolean singIn, Map<String, Object> pageVariables, String sessionId) {
        pageVariables.put("singIn", PageGenerator.wrapperBoolean(singIn));
        String name = handlerIdSession.getNameBySessionId(sessionId);
        pageVariables.put("userName", name == null ? "" : name);
    }

}
