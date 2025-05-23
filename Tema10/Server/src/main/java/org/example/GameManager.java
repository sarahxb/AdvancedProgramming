package org.example;

import java.util.*;

public class GameManager {
    private final Map<String, Game> games = new HashMap<>();

    public synchronized Game createGame(String gameId, int boardSize, long timeMillis) {
        if (games.containsKey(gameId)) {
            return games.get(gameId);
        }
        Game game = new Game(gameId, boardSize, timeMillis);
        games.put(gameId, game);
        return game;
    }

    public synchronized Game getGame(String gameId) {
        return games.get(gameId);
    }


}

