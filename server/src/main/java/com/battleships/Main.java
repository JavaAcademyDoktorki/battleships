package com.battleships;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            new Server();
        } catch (IOException e) {
            logger.error("Problem przy próbie odpalenia serwera.", e);
        }
    }
}
