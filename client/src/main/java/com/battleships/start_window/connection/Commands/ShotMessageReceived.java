package com.battleships.start_window.connection.Commands;

import com.battleships.commands.Values.Shot;

public class ShotMessageReceived<V> extends AbstractServerCommand {
    public ShotMessageReceived(V value) {
        super(value);
    }

    @Override
    public void execute() {
        Shot shot = (Shot) value;
        System.out.println("received shot: " + shot); // TODO usunac sout, dopracować metodę
    }
}
