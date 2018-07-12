package com.battleships.start_window.connection;

import com.battleships.commands.PlayerCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageQueue {

    private static final int QUEUE_CAPACITY = 100;
    private final BlockingQueue<PlayerCommand<?>> messageQueue;
    private static final Logger logger = LogManager.getLogger(MessageQueue.class);


    public MessageQueue() {
        messageQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    }

    public PlayerCommand<?> take() throws InterruptedException {
        return messageQueue.take();
    }
}
