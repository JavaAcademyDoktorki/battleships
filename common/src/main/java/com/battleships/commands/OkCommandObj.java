package com.battleships.commands;

import java.io.Serializable;

public class OkCommandObj implements Serializable {
    public String message;

    public OkCommandObj(String message) {
        this.message = message;
    }
}
