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

    void sendCommand(String command) {
        clientWriter.println(command);
        clientWriter.flush();
    }
    boolean hasNextCommand() {
        return clientInput.hasNextLine();
    }

    String nextCommand() {
        return clientInput.nextLine();
    }
}
