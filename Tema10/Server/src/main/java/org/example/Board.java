package org.example;

import java.util.*;

public class Board {
    private final int size;
    private final String[][] grid;
    private static final int[][] NEIGHBORS = {
            {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}
    };

    public Board(int size) {
        this.size = size;
        this.grid = new String[size][size];
    }

    public synchronized boolean placeMove(int x, int y, String playerName) {
        if (isValidPosition(x, y)) {
            if (grid[y][x] == null) {
                grid[y][x] = playerName;
                return true;
            }
        }
        return false;
    }


    public synchronized boolean checkWin(String playerName, boolean isFirstPlayer) {
        if (isFirstPlayer) {

            for (int i = 0; i < size; i++) {
                if (grid[i][0] != null && grid[i][0].equals(playerName)) {
                    if (hasPath(i, 0, playerName, new boolean[size][size], true)) {
                        return true;
                    }
                }
            }
        } else {

            for (int i = 0; i < size; i++) {
                if (grid[0][i] != null && grid[0][i].equals(playerName)) {
                    if (hasPath(0, i, playerName, new boolean[size][size], false)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean hasPath(int x, int y, String playerName, boolean[][] visited, boolean isFirstPlayer) {
        if (isFirstPlayer && y == size - 1) return true; // Player1 reaches right edge
        if (!isFirstPlayer && x == size - 1) return true; // Player2 reaches bottom edge
        
        visited[x][y] = true;
        
        for (int[] neighbor : NEIGHBORS) {
            int newX = x + neighbor[0];
            int newY = y + neighbor[1];
            
            if (isValidPosition(newX, newY) && !visited[newX][newY] 
                && grid[newX][newY] != null 
                && grid[newX][newY].equals(playerName)) {
                if (hasPath(newX, newY, playerName, visited, isFirstPlayer)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }


}

