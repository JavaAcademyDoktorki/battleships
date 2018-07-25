package com.battleships.player;

import com.battleships.commands.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class Player {
    private final Socket playerSocket;
    private String playerName;
    private PlayerIO playerIO;
    private boolean playerNameDifferentThanGiven;

    public Player(Socket playerSocket) throws IOException {
        this.playerSocket = playerSocket;
        this.playerIO = new PlayerIO(playerSocket);
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

    public String getPlayerName() {
        return playerName;
    }

    public boolean isPlayerNameDifferentThanGiven() {
        return playerNameDifferentThanGiven;
    }

    public void setPlayerNameSameAsGiven(boolean playerNameDifferentThanGiven) {
        this.playerNameDifferentThanGiven = playerNameDifferentThanGiven;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }
}
