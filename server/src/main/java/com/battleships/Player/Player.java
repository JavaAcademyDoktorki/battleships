package com.battleships.Player;

import com.battleships.commands.PlayerCommand;
import com.battleships.commands.PlayerStatus;

import java.io.IOException;
import java.net.Socket;

public class Player {
    private final Socket playerSocket;
    private String playerName;
    private PlayerIO playerIO;
    private final PlayerStatus playerStatus;

    public Player(Socket playerSocket, PlayerStatus playerStatus) throws IOException {
        this.playerSocket = playerSocket;
        this.playerIO = new PlayerIO(playerSocket);
        this.playerStatus = playerStatus;
    }

    public void sendCommand(PlayerCommand<?> command) {
        playerIO.sendCommand(command);
    }

    public <V> PlayerCommand<V> nextCommand() {
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

    public boolean isActive() {
        return playerStatus == PlayerStatus.ACTIVE;
    }

    public boolean isInActive() {
        return !isActive();
    }
}
