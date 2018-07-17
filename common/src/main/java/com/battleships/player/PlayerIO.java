package com.battleships.player;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class PlayerIO {
    private final PrintWriter clientWriter;
    private final Scanner clientScanner;

    PlayerIO(Socket playerSocket) throws IOException {
        clientWriter = new PrintWriter(playerSocket.getOutputStream());
        clientScanner = new Scanner(playerSocket.getInputStream());
    }

    void sendCommand(String command) {
        clientWriter.println(command);
        clientWriter.flush();
    }

    String nextUserCommand() {
        return clientScanner.nextLine();
    }
}
