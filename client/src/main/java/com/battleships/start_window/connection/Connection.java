package com.battleships.start_window.connection;

import com.battleships.Commands;
import com.battleships.LogMessages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;
import java.util.Scanner;

public enum Connection {
    INSTANCE;
    private Optional<Socket> socket = Optional.empty();
    private PrintWriter socketWriter;
    private Scanner socketScanner;
    private final static Logger logger = LogManager.getLogger(Connection.class);
    private Thread readCommandsFromUserThread;

    public void disconnect() {
        if (isConnected()) {
            try {
                sendToServer(Commands.STOP_PLAYING.name());
                socket.get().close();
                logger.info(LogMessages.DISCONNECTED_AFTER_PLAYER_REQ);
            } catch (IOException e) {
               logger.error(LogMessages.PROBLEM_WHEN_TRYING_TO_DISCONNECT + e.getMessage());
            } finally {
                socket = Optional.empty();
            }
        }
    }

    private boolean isConnected() {
        return socket.isPresent() && socket.get().isConnected();
    }

    public void connect(ConnectionInfo connectionInfo, String playerName) {
        if (!isConnected()) {
            try {
                socket = Optional.of(new Socket(connectionInfo.getIp(), connectionInfo.getPort()));
                socketWriter = new PrintWriter(socket.get().getOutputStream());
                socketScanner = new Scanner(socket.get().getInputStream());
                sendToServer(playerName);
                initThreadReadingCommandsFromServer();
                startThreadReadingCommandsFromServer();
            } catch (IOException e) {
                logger.error(String.format(LogMessages.CANNOT_CONNECT_TO_SERVER, connectionInfo.getIp(), connectionInfo.getPort()));
            }
        }
    }

    private void initThreadReadingCommandsFromServer() {
        readCommandsFromUserThread = new Thread(() ->{
            while (isConnected()) {
                tryThreadSleep(100);
                if (socketScanner.hasNextLine()) {
                    logger.info(socketScanner.nextLine());
                }
            }
        });
    }

    private void startThreadReadingCommandsFromServer() {
        readCommandsFromUserThread.start();
    }

    private void tryThreadSleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendToServer(String command) {
        if (isConnected()) {
            socketWriter.println(command);
            socketWriter.flush();
        }
        else{
            logger.error(String.format(LogMessages.UNABLE_TO_SEND_MESSAGE, command));
        }
    }
}
