package accauntService;

/**
 * Created by Агент on 08.08.15.
 */


import base.AccauntService;
import main.UserProfile;
import utils.TimeHelper;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class AccauntServiceImpl implements AccauntService  {

    private Map<String, String> sessions = new HashMap<>();
    private Map<String, UserProfile> users = new HashMap<>();

    public UserProfile getUser ( String name) {
        UserProfile user = this.users.get( name );
        return user;
    }

    public boolean addUser (  UserProfile user) {
        if ( getUser ( user.getName() ) == null ) {
            this.users.put(user.getName(), user);
            return true;
        }
        else return false;
    }

    public boolean checkUser( UserProfile user ) {
        UserProfile currentUser = users.get(user.getName());
        if ( currentUser == null ) return false;
        if ( currentUser.getPassword().equals( user.getPassword()) ) return true;
        return false;
    }

    public String getNameBySessionId(String sessionId) {
        return sessions.get( sessionId );
    }

    public void  removeSessionId(String sessionId) {
        sessions.remove(sessionId);
    }

    public void addSessionId(String sessionId,String name) {
        sessions.put(sessionId, name );
    }

}




