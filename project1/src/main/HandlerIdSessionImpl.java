package main;

import base.AccauntService;
import base.HandlerIdSession;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpCookie;
import java.util.List;

/**
 * Created by Агент on 03.09.15.
 */

public class HandlerIdSessionImpl implements HandlerIdSession {

    public AccauntService accauntService;

    public HandlerIdSessionImpl(AccauntService accauntService) {
        this.accauntService = accauntService;
    }

    public String getSessionId( HttpServletRequest request) {
        return request.getSession().getId();
    }

    public boolean checkInput (String sessionId) {
        if ( accauntService.getNameBySessionId(sessionId) == null ) return false;
        return true;
    }

    public String getNameBySessionId(String sessionId) {
        return accauntService.getNameBySessionId(sessionId);
    }

    public void addSessionId(String sessionId, String name) {
        accauntService.addSessionId(sessionId, name);
    }

    public void removeSessionId(String sessionId) {
        accauntService.removeSessionId(sessionId);
    }

}
