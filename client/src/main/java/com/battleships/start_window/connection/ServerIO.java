package com.battleships.start_window.connection;

import com.battleships.PlayerCommand;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Optional;
import java.util.Scanner;

class ServerIO {
    private final ObjectOutputStream socketWriter;
    private final Scanner socketScanner;

    ServerIO(ObjectOutputStream socketWriter, Scanner socketScanner) {
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

    void trySend(PlayerCommand playerCommand) {
        try {
            socketWriter.writeObject(playerCommand);
        } catch (IOException e) {
            // TODO 16.07.2018 handle - Damian
            e.printStackTrace();
        }
    }
}
