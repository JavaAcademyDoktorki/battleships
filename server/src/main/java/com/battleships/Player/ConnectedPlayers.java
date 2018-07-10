package com.battleships.Player;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectedPlayers {
    private final List<Player> playerList;
    private int anonymousPlayersCounter = 0;

    public ConnectedPlayers() {
        this.playerList = new CopyOnWriteArrayList<>();
    }

    public void add(Player player) {
        playerList.add(player);
    }

    public void remove(Player player) {
        playerList.remove(player);
    }

    public boolean isNameAvailable(String name){
        for (Player player : playerList){
            if (player.isSameName(name)){
                return false;
            }
        }
        return true;
    }

    public String generateNewName(){
        ++anonymousPlayersCounter;
        return "Player" + anonymousPlayersCounter;
    }

    Player getLastAddedPlayer() {
        return playerList.get(playerList.size()-1);
    }
}
