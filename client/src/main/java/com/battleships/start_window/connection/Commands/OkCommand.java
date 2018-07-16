package com.battleships.start_window.connection.Commands;

import com.battleships.commands.OkCommandObj;
import com.battleships.commands.OkCommandValue;

public class OkCommand<V> extends AbstractServerCommand {
    protected OkCommand(V value) {
        super(value);
    }

    @Override
    public void execute() {
        OkCommandValue value = (OkCommandValue) this.value;
        OkCommandObj okCommandObj = value.message;
        System.out.println(okCommandObj.message);
    }
}
