package com.battleships.start_window.connection.Commands;

public class StartGameWindow<V> extends AbstractServerCommand {

    protected StartGameWindow(V value) {
        super(value);
    }

    @Override
    public void execute() {

        Boolean value = (Boolean) this.value;
        if(value)
            System.out.println("I am moving right now");
        else
            System.out.println("starting and waiting for opponents move!");
    }
}
