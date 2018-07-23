package com.battleships.player;

import com.battleships.commands.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class PlayerIO {
    private final ObjectOutputStream clientObjectWriter;
    private final ObjectInputStream clientObjectReader;

    PlayerIO(Socket playerSocket) throws IOException {
        clientObjectWriter = new ObjectOutputStream(playerSocket.getOutputStream());
        clientObjectReader = new ObjectInputStream(playerSocket.getInputStream());
    }

    void sendCommand(Message command) {
        try {
            clientObjectWriter.writeObject(command);
            clientObjectWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();    // todo obsłużyć ładniej
        }
    }

    Message nextUserCommand() {
        try {
            return (Message) clientObjectReader.readObject(); // TODO 16.07 fix unchecked cast - krzychu
        } catch (IOException | ClassNotFoundException e) {
            // TODO 16.07.2018 handle - Damian
            e.printStackTrace();
        }
        return null; // TODO 16.07.2018 do not return null - Damian
    }
}
