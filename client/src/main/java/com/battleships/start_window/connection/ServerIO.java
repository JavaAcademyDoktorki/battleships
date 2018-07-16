package com.battleships.start_window.connection;

import com.battleships.commands.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class ServerIO {
    private final ObjectOutputStream socketWriter;
    private final ObjectInputStream socketReader;

    ServerIO(ObjectOutputStream socketWriter, ObjectInputStream socketReader) {
        this.socketWriter = socketWriter;
        this.socketReader = socketReader;
    }


    Message<?> getMessage() {
        Message<?> messageFromServer = null;
        try {
            messageFromServer = (Message<?>) socketReader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return messageFromServer;
    }

    void trySend(Message message) {
        try {
            socketWriter.writeObject(message);
        } catch (IOException e) {
            // TODO 16.07.2018 handle - Damian
            e.printStackTrace();
        }
    }
}
