package com.battleships;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

class PlayerIO {
    private final PrintWriter clientWriter;
    private final ObjectInputStream clientObjectReader;

    PlayerIO(Socket playerSocket) throws IOException {
        clientWriter = new PrintWriter(playerSocket.getOutputStream());
        clientObjectReader = new ObjectInputStream(playerSocket.getInputStream());
    }

    void sendCommand(String command) {
        clientWriter.println(command);
        clientWriter.flush();
    }

    boolean isNextUserRequestAvailable() {
        try {
            return clientObjectReader.available() > 0; // TODO 16.07.2018 is it a good way to check is object available ?  - Damian
        } catch (IOException e) {
            // TODO 16.07.2018 handle - Damian
            e.printStackTrace();
        }
        return false;
    }

    PlayerCommand nextUserCommand() {

        try {
            return (PlayerCommand) clientObjectReader.readObject();
        } catch (IOException e) {
            // TODO 16.07.2018 handle - Damian
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO 16.07.2018 handle - Damian
            e.printStackTrace();
        }
        return null; // TODO 16.07.2018 do not return null - Damian
    }
}
