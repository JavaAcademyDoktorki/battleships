package com.battleships.player;

import com.battleships.commands.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectedPlayers {
    private final List<Player> playerList;
    private int anonymousPlayersCounter = 0;

    public ConnectedPlayers() {
        this.playerList = new CopyOnWriteArrayList<>();
    }

    public Player playerForSocket(Socket socket) throws IOException {
        Player player = new Player(socket);
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
        return playerList.get(0);
    }

    public void sendToInactive(Message stringPlayerCommand) {
        Player inactive = getInactive();
        inactive.sendCommand(stringPlayerCommand);
    }

    private Player getInactive() {
        if (playerList.size() == 2)
            return playerList.get(1);
        else
            throw new IllegalStateException();
    }

    public void switchActive() {
        Collections.reverse(playerList);
    }

    public boolean areThereAnyPlayer() {
        return playerList.size() > 0;
    }
}
