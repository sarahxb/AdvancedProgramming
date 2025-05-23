package org.example;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket clientSocket;
    private GameServer server;
    private GameManager gameManager;
    private String playerName = null;
    private Game currentGame = null;

    public ClientThread(Socket clientSocket, GameServer server, GameManager gameManager) {
        this.clientSocket = clientSocket;
        this.server = server;
        this.gameManager = gameManager;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String request;
            while ((request = in.readLine()) != null) {
                String[] tokens = request.split(" ");
                String command = tokens[0];

                switch (command.toLowerCase()) {
                    case "create":
                        String gameId = tokens[1];
                        int boardSize = Integer.parseInt(tokens[2]);
                        long timeMillis = Long.parseLong(tokens[3]);
                        currentGame = gameManager.createGame(gameId, boardSize, timeMillis);
                        playerName = "Player1";
                        Player player = new Player(playerName, clientSocket, timeMillis);
                        currentGame.addPlayer(player);
                        out.println("Game " + gameId + " created and joined as " + playerName);
                        out.println("");
                        break;

                    case "join":
                        gameId = tokens[1];
                        currentGame = gameManager.getGame(gameId);
                        if (currentGame != null) {
                            playerName = "Player2";
                            if (currentGame.addPlayer(new Player(playerName, clientSocket, 60000))) {
                                out.println("Joined game " + gameId + " as " + playerName);


                                out.println("");
                            } else {
                                playerName = null;
                                currentGame = null;
                                out.println("Could not join game - game is full.");
                                out.println("");
                            }
                        } else {
                            out.println("Game " + gameId + " not found.");
                            out.println("");
                        }
                        break;

                    case "move":
                        if (playerName == null || currentGame == null) {
                            out.println("You are not in a game.");
                            out.println("");
                            break;
                        }

                        try {
                            if (tokens.length != 3) {
                                out.println("Invalid move format. Use: move x y");
                                out.println("");
                                break;
                            }

                            int x = Integer.parseInt(tokens[1]);
                            int y = Integer.parseInt(tokens[2]);
                            
                            String moveResult = currentGame.submitMove(playerName, x, y);
                            out.println(moveResult);
                            out.println("");

                        } catch (NumberFormatException e) {
                            out.println("Invalid move format. Coordinates must be numbers.");
                            out.println("");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            out.println("Invalid move format. Use: move x y");
                            out.println("");
                        }
                        break;

                    case "stop":
                        out.println("Server stopped");
                        out.println("");
                        server.stopServer();
                        return;

                    default:
                        out.println("Unknown command.");
                        out.println("");
                }
            }

        } catch (IOException e) {
            System.err.println("ClientThread error: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Failed to close socket: " + e.getMessage());
            }
        }
    }
}
