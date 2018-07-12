package com.battleships.start_window.connection;

import com.battleships.commands.PlayerCommand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;
import java.util.Scanner;

class ServerIO {
    private final ObjectOutputStream socketWriter;
    private final ObjectInputStream socketReader;

    ServerIO(ObjectOutputStream socketWriter, ObjectInputStream socketReader) {
        this.socketWriter = socketWriter;
        this.socketReader = socketReader;
    }


    Optional<PlayerCommand<?>> getMessageOptional() {
        Optional <PlayerCommand<?>> messageOptional = Optional.empty();
        PlayerCommand<?> messageFromServer = null;
        try {
            messageFromServer = (PlayerCommand<?>) socketReader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        messageOptional = Optional.of(messageFromServer);
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
