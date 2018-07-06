package com.battleships.Commands.CommandsImpl;

import com.battleships.Commands.AbstractCommand;
import com.battleships.Player.Player;

public class StartPlaying extends AbstractCommand {

    public StartPlaying(String value) {
        super(value);
    }

    @Override
    public void execute(Player player) {
        System.out.println("START PLAYING");
    }
}
