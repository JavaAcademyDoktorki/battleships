package com.battleships.player;

import com.battleships.commands.Message;

import java.io.IOException;
import java.net.Socket;

public class Player {
    private final Socket playerSocket;
    private String playerName;
    private PlayerIO playerIO;
    private PlayerStatus playerStatus;
    private boolean playerNameDifferentThanGiven;

    public Player(Socket playerSocket, PlayerStatus playerStatus) throws IOException {
        this.playerSocket = playerSocket;
        this.playerIO = new PlayerIO(playerSocket);
        this.playerStatus = playerStatus;
    }

    public void sendCommand(Message command) {
        playerIO.sendCommand(command);
    }

    public Message nextCommand() {
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

    public boolean isSameName(String name) {
        return playerName.equals(name);
    }

    public boolean isActive() {
        return playerStatus == PlayerStatus.ACTIVE;
    }

    public boolean isInActive() {
        return !isActive();
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isPlayerNameDifferentThanGiven() {
        return playerNameDifferentThanGiven;
    }

    public void setPlayerNameSameAsGiven(boolean playerNameDifferentThanGiven) {
        this.playerNameDifferentThanGiven = playerNameDifferentThanGiven;
    }

    public void switchActive() {
        this.playerStatus = playerStatus.other();
    }
}
