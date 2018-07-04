package com.battleships;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class PlayerIO {
    private final PrintWriter clientWriter;
    private final Scanner clientInput;

    PlayerIO(Socket playerSocket) throws IOException {
        clientWriter = new PrintWriter(playerSocket.getOutputStream());
        clientInput = new Scanner(playerSocket.getInputStream());
    }

    void sendMessage(String message) {
        clientWriter.println(message);
        clientWriter.flush();
    }
    boolean hasNextMessage() {
        return clientInput.hasNextLine();
    }

    String nextMessage() {
        return clientInput.nextLine();
    }
}
