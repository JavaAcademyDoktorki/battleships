package com.battleships;

import com.battleships.Messages.LogMessages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    /**
     * Initialiazes server
     *
     * @param args - possible String array with arguments
     */
    public static void main(String[] args) {
        try {
            new Server();
        } catch (IOException e) {
            logger.error(LogMessages.PROBLEM_WHEN_TRYING_TO_RUN_SERVER, e);
        }
    }
}
