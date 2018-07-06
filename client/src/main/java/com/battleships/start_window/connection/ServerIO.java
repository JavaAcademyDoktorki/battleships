package com.battleships.start_window.connection;

import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

class ServerIO {
    private PrintWriter socketWriter;
    private Scanner socketScanner;

    ServerIO(PrintWriter socketWriter, Scanner socketScanner) {
        this.socketWriter = socketWriter;
        this.socketScanner = socketScanner;
    }


    Optional<String> getMessageOptional() {
        Optional <String> messageOptional = Optional.empty();
        if (socketScanner.hasNextLine()) {
            String messageFromServer = socketScanner.nextLine();
            messageOptional = Optional.of(messageFromServer);
        }
        return messageOptional;
    }

    void send(String message) {
        socketWriter.println(message);
        socketWriter.flush();
    }
}
