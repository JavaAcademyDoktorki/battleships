package com.battleships.player;

import com.battleships.commands.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectedPlayers {
    private final List<Player> playerList;
    private int anonymousPlayersCounter = 0;
    private PlayerStatus playerStatus = PlayerStatus.ACTIVE;

    public ConnectedPlayers() {
        this.playerList = new CopyOnWriteArrayList<>();
    }

    public Player playerForSocket(Socket socket) throws IOException {
        Player player = new Player(socket, playerStatus);
        playerStatus = playerStatus.other();
        return player;
    }

    public void add(Player player) {
        playerList.add(player);
    }

    public void remove(Player player) {
        playerList.remove(player);
    }

    public boolean isNameAvailable(String name) {
        for (Player player : playerList) {
            if (player.isSameName(name)) {
                return false;
            }
        }
        return true;
    }

    public String generateNewName() {
        ++anonymousPlayersCounter;
        return "Player" + anonymousPlayersCounter;
    }

    public boolean notFull() {
        return playerList.size() < 2;
    }

    public void sendToActive(Message playerCommand) {
        Player active = getActive();
        active.sendCommand(playerCommand);
    }

    public Player getActive() {
        if (playerList.get(0).isActive())
            return playerList.get(0);
        else
            return playerList.get(1);
    }

    public void sendToInactive(Message stringPlayerCommand) {
        Player inactive = getInactive();
        inactive.sendCommand(stringPlayerCommand);
    }

    private Player getInactive() {
        if (playerList.get(0).isInActive())
            return playerList.get(0);
        else
            return playerList.get(1);
    }

    public void switchActive() {
        playerList.get(0).switchActive();
        playerList.get(1).switchActive();
    }
}
