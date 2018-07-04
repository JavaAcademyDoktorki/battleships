package com.battleships;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class ConnectedPlayers {
    private final List<Player> playerList;
    private int anonymousPlayersCounter = 0;

    ConnectedPlayers() {
        this.playerList = new CopyOnWriteArrayList<>();
    }

    void add(Player player) {
        playerList.add(player);
    }

    void remove(Player player) {
        playerList.remove(player);
    }

    boolean isNameAvailable(String name){
        for (Player player : playerList){
            if (player.isNamed(name)){
                return false;
            }
        }
        return true;
    }

    String generateNewName(){
        ++anonymousPlayersCounter;
        return "Player" + anonymousPlayersCounter;
    }
}
