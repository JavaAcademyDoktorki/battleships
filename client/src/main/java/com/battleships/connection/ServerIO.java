package com.battleships.connection;

import com.battleships.commands.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class ServerIO {
    private final ObjectOutputStream socketWriter;
    private final ObjectInputStream socketReader;
    private static final Logger logger = LogManager.getLogger(ServerIO.class);

    ServerIO(ObjectOutputStream socketWriter, ObjectInputStream socketReader) {
        this.socketWriter = socketWriter;
        this.socketReader = socketReader;
    }


    Message<?> getMessage() {
        Message<?> messageFromServer = null;
        try {
            messageFromServer = (Message<?>) socketReader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.info("odłączono od serwera");
            logger.error(() -> e.getMessage());
        }
        return messageFromServer;
    }

    void trySend(Message message) {
        try {
            socketWriter.writeObject(message);
        } catch (IOException e) {
            logger.error(String.format("błąd zapisu do gniazda: %s%n", e.getMessage()));
        }
    }
}
