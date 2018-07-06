package com.battleships;
import com.battleships.commands.PlayerCommand;

import java.io.IOException;
import java.net.Socket;

class Player {
    private final Socket playerSocket;
    private String playerName;
    private PlayerIO playerIO;

    private Player(Socket playerSocket) throws IOException {
        this.playerSocket = playerSocket;
        this.playerIO = new PlayerIO(playerSocket);
    }

    static Player createForSocket(Socket socket) throws IOException {
        return new Player(socket);
    }

    void sendCommand(String command) {
        playerIO.sendCommand(command);
    }

    PlayerCommand nextCommand() {
        return playerIO.nextUserCommand();
    }

    void disconnect() throws IOException {
        playerSocket.close();
    }

    void setName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return playerName;
    }

    boolean isSameName(String name) {
        return playerName.equals(name);
    }
}
