package base;

import main.UserProfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Агент on 21.08.15.
 */
public interface AccauntService {

    public UserProfile getUser ( String name);
    public boolean addUser ( UserProfile userProfile);
    public boolean checkUser( UserProfile user );

    public String getNameBySessionId(String sessionId);
    public void  removeSessionId(String sessionId);
    public void addSessionId(String sessionId,String name);


}
