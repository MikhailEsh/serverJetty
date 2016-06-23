package base;

/**
 * Created by Агент on 03.09.15.
 */

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpCookie;
import java.util.List;

public interface HandlerIdSession {
    public String getSessionId( HttpServletRequest request);
    public boolean checkInput (String sessionId);
    public String getNameBySessionId(String sessionId);
    public void addSessionId(String sessionId, String name);
    public void removeSessionId(String sessionId);
}
