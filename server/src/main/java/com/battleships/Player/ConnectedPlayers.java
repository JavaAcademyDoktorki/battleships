package com.battleships.Player;

import com.battleships.commands.PlayerCommand;
import com.battleships.commands.PlayerStatus;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ConnectedPlayers {
    private final List<Player> playerList;
    private int anonymousPlayersCounter = 0;
    PlayerStatus playerStatus = PlayerStatus.values()[ThreadLocalRandom.current().nextInt(0, 2)];

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

    Player getLastAddedPlayer() {
        return playerList.get(playerList.size() - 1);
    }

    public boolean notFull() {
        return playerList.size() < 2;
    }

    public void sendToActive(PlayerCommand<String> stringPlayerCommand) {
        Player active = getActive();
        active.sendCommand(stringPlayerCommand);
    }

    private Player getActive() {
        if (playerList.get(0).isActive())
            return playerList.get(0);
        else
            return playerList.get(1);
    }

    public void sendToInactive(PlayerCommand<String> stringPlayerCommand) {
        Player inactive = getInactive();
        inactive.sendCommand(stringPlayerCommand);
    }

    private Player getInactive() {
        if (playerList.get(0).isInActive())
            return playerList.get(0);
        else
            return playerList.get(1);
    }
}
