package com.battleships;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Server myServer = new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
