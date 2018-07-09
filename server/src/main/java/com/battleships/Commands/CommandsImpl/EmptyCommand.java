package com.battleships.Commands.CommandsImpl;

import com.battleships.Commands.AbstractCommand;
import com.battleships.Player.Player;

public class EmptyCommand extends AbstractCommand {

    public EmptyCommand(String value) {
        super(value);
    }

    @Override
    public void execute(Player player) {
    }
}
