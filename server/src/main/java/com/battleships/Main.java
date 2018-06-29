package com.battleships;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new Server();
        } catch (IOException e) {
            // todo obsłużyć
            e.printStackTrace();
        }
    }
}
