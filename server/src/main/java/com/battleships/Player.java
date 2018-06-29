package com.battleships;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class Player {
    private final Socket playerSocket;
    private final PrintWriter clientWriter;
    private final Scanner clientInput;
    private String playerName;

    private Player(Socket playerSocket) throws IOException {
        this.playerSocket = playerSocket;
        clientWriter = new PrintWriter(playerSocket.getOutputStream());
        clientInput = new Scanner(playerSocket.getInputStream());
    }

    static Player of(Socket socket) throws IOException {
        return new Player(socket);
    }

    void sendMessage(String message) {
        clientWriter.println(message);
        clientWriter.flush();
    }

    boolean hasNextMessage() {
        return clientInput.hasNextLine();
    }

    String getNextMessage() {
        return clientInput.nextLine();
    }

    void disconnect() throws IOException {
        playerSocket.close();
    }

    void setName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return playerName;
    }
}
