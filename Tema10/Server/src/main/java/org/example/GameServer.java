package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class GameServer {
    public static final int port = 8100;
    private volatile boolean running = true;
    private ServerSocket serverSocket = null;

    private final GameManager gameManager = new GameManager();

    public GameServer() throws IOException {
        try {
            System.out.println("Creating server socket...");
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (running) {
                try {
                    System.out.println("Waiting for a client on port " + port + "...");
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("New client connected from " + clientSocket.getInetAddress());
                    
                    new ClientThread(clientSocket, this, gameManager).start();
                } catch (SocketException e) {
                    if (running) {
                        System.err.println("Socket error: " + e.getMessage());
                    }
                } catch (IOException e) {
                    if (running) {
                        System.err.println("IO error: " + e.getMessage());
                    }
                }
            }

        } finally {
            closeServerSocket();
        }
    }

    private void closeServerSocket() {
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
                System.out.println("Server socket closed.");
            } catch (IOException e) {
                System.err.println("Error closing server socket: " + e.getMessage());
            }
        }
    }

    public void stopServer() {
        running = false;
        closeServerSocket();
    }

    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down server...");
            server.stopServer();
        }));
    }
}
