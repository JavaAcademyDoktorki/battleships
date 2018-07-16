package com.battleships.commands;

import java.io.Serializable;

public class OkCommandValue implements Serializable {
    public OkCommandObj message;

    public OkCommandValue(OkCommandObj message) {
        this.message = message;
    }
}
