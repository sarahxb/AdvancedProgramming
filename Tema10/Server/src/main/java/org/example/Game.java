package org.example;


public class Game {
    private final String gameId;
    private final Player[] players = new Player[2];
    private final Board board;
    private int currentPlayerIndex = 0;
    private boolean gameStarted = false;
    private boolean gameEnded = false;
    private String winner = null;
    private long lastMoveTimestamp;
    private static final long TIME_PER_MOVE = 30000;

    public Game(String gameId, int boardSize, long initialTimeMillis) {
        this.gameId = gameId;
        this.board = new Board(boardSize);
        this.players[0] = null;
        this.players[1] = null;
        this.lastMoveTimestamp = System.currentTimeMillis();
    }

    public synchronized boolean addPlayer(Player player) {
        if (players[0] == null) {
            players[0] = player;
            return true;
        } else if (players[1] == null && !players[0].getName().equals(player.getName())) {
            players[1] = player;
            gameStarted = true;
            lastMoveTimestamp = System.currentTimeMillis();
            return true;
        }
        return false;
    }



    public synchronized String submitMove(String playerName, int x, int y) {
        if (gameEnded) {
            return "Game has ended. Winner: " + winner;
        }

        if (!gameStarted) {
            return "Game hasn't started yet.";
        }

        long now = System.currentTimeMillis();
        Player currentPlayer = players[currentPlayerIndex];

        if (!currentPlayer.getName().equals(playerName)) {
            return "Not your turn. Current player: " + currentPlayer.getName();
        }

        long elapsed = now - lastMoveTimestamp;
        if (elapsed > TIME_PER_MOVE) {
            gameEnded = true;
            winner = players[1 - currentPlayerIndex].getName();
            return playerName + " has run out of time. Game lost.";
        }

        boolean moveAccepted = board.placeMove(x, y, playerName);
        if (!moveAccepted) {
            return "Invalid move. Position (" + x + "," + y + ") is either occupied or out of bounds.";
        }


        boolean isFirstPlayer = playerName.equals(players[0].getName());
        if (board.checkWin(playerName, isFirstPlayer)) {
            gameEnded = true;
            winner = playerName;
            return playerName + " has won the game!";
        }

        currentPlayerIndex = 1 - currentPlayerIndex;
        lastMoveTimestamp = now;
        
        return "Move accepted. " + players[currentPlayerIndex].getName() + "'s turn.";
    }




}

