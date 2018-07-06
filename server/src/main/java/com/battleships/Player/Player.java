package com.battleships.Player;
import com.battleships.commands.PlayerCommand;

import java.io.IOException;
import java.net.Socket;

public class Player {
    private final Socket playerSocket;
    private String playerName;
    private PlayerIO playerIO;

    private Player(Socket playerSocket) throws IOException {
        this.playerSocket = playerSocket;
        this.playerIO = new PlayerIO(playerSocket);
    }

    public static Player createForSocket(Socket socket) throws IOException {
        return new Player(socket);
    }

    public void sendCommand(String command) {
        playerIO.sendCommand(command);
    }

    public PlayerCommand nextCommand() {
        return playerIO.nextUserCommand();
    }

    public void disconnect() throws IOException {
        playerSocket.close();
    }

    public void setName(String playerName) {
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
