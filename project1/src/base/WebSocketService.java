package base;

/**
 * Created by misha on 19.09.15.
 */

import game.GameWebSocket;

public interface WebSocketService {

    void addUser(GameWebSocket user);

    void notifyMyNewScore(GameUser user);

    void notifyEnemyNewScore(GameUser user);

    void notifyStartGame(GameUser user);

    void notifyGameOver(GameUser user, boolean win);
}
