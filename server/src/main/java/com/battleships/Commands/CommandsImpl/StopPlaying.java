package com.battleships.Commands.CommandsImpl;

import com.battleships.Commands.AbstractCommand;
import com.battleships.Player.Player;

public class StopPlaying extends AbstractCommand {

    public StopPlaying(String value) {
        super(value);
    }

    @Override
    public void execute(Player player) {
        System.out.println("STOP PLAYING");
    }
}
