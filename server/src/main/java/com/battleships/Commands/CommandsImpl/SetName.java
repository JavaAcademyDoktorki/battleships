package com.battleships.Commands.CommandsImpl;

import com.battleships.Commands.AbstractCommand;
import com.battleships.Player.Player;

public class SetName extends AbstractCommand {

    public SetName(String value) {
        super(value);
    }

    @Override
    public void execute(Player player) {
        System.out.println("SETTING NAME");
    }


}
