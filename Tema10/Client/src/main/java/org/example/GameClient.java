package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8100;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Connected to the server. Type commands or 'exit' to quit.");

            while (true) {
                System.out.print("> ");
                String command = scanner.nextLine();

                if ("exit".equalsIgnoreCase(command.trim())) {
                    System.out.println("Client stopped.");
                    break;
                }

                out.println(command);

                String line;
                while ((line = in.readLine()) != null && !line.isEmpty()) {
                    System.out.println("Server response: " + line);
                }
            }

        } catch (IOException e) {
            System.err.println("Error in GameClient: " + e.getMessage());
        }
    }
}
