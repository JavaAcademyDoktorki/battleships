package com.battleships.start_window.connection.Commands;

import com.battleships.commands.Values.Shot;

public class ShotMessageReceived extends AbstractServerCommand {
    public <V> ShotMessageReceived(V value) {
        super(value);
    }

    @Override
    public void execute() {
        Shot shot = (Shot) value;
        System.out.println("received shot: " + shot);
    }
}
