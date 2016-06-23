package game;

import base.WebSocketService;
import base.GameUser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by misha on 18.09.15.
 */
public class WebSocketServiceImpl implements WebSocketService {
    private Map<String, GameWebSocket> userSockets = new HashMap<>();

    public void addUser(GameWebSocket user) {
        userSockets.put(user.getMyName(), user);
    }

    public void notifyMyNewScore(GameUser user) {
        userSockets.get(user.getMyName()).setMyScore(user);
    }

    public void notifyEnemyNewScore(GameUser user) {
        userSockets.get(user.getMyName()).setEnemyScore(user);
    }

    public void notifyStartGame(GameUser user) {
        GameWebSocket gameWebSocket = userSockets.get(user.getMyName());
        gameWebSocket.startGame(user);
    }

    @Override
    public void notifyGameOver(GameUser user, boolean win) {
        userSockets.get(user.getMyName()).gameOver(user, win);
    }
}
