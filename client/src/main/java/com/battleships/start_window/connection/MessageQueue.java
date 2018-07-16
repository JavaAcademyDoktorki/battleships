package com.battleships.start_window.connection;

import com.battleships.commands.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageQueue {

    private static final int QUEUE_CAPACITY = 100;
    private final BlockingQueue<Message<?>> messageQueue;
    private static final Logger logger = LogManager.getLogger(MessageQueue.class);


    public MessageQueue() {
        messageQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    }

    public Message<?> take() throws InterruptedException {
        return messageQueue.take();
    }
}
