package com.battleships.commands;

import com.battleships.commands.Values.PlayersShootCommandValue;
import com.battleships.player.Player;

public class PlayersShootCommand<V> extends AbstractCommand {
    public PlayersShootCommand(V value) {
        super(value);
    }

    @Override
    public void execute(Player playerWhoShot) {
        PlayersShootCommandValue playersShotVal = (PlayersShootCommandValue) this.value;

    }

}
