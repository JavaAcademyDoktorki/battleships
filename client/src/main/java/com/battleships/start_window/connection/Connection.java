package com.battleships.start_window.connection;

import com.battleships.Command;
import com.battleships.CommunicatingProtocol;
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
            tryToDisconnectFromServer();
        }
    }

    private void tryToDisconnectFromServer() {
        try {
            sendToServer(Command.STOP_PLAYING);
            disconnectPlayerOrLogIfFailed();
        } catch (IOException e) {
            logger.error(LogMessages.PROBLEM_WHEN_TRYING_TO_DISCONNECT + e.getMessage());
        } finally {
            socket = Optional.empty();
        }
    }

    private void disconnectPlayerOrLogIfFailed() throws IOException {
        if (socket.isPresent()) {
            socket.get().close();
            logger.info(LogMessages.DISCONNECTED_AFTER_PLAYER_REQ_SUCCEED);
        } else {
            logger.info(LogMessages.DISCONNECTED_AFTER_PLAYER_REQ_FAILED);
        }
    }

    private boolean isConnected() {
        return socket.isPresent() && socket.get().isConnected();
    }

    public void establishConnection(ConnectionInfo connectionInfo, String playerName) {
        if (!isConnected()) {
            tryToEstablishConnection(connectionInfo, playerName);
        }
    }

    private void tryToEstablishConnection(ConnectionInfo connectionInfo, String playerName) {
        try {
            socket = Optional.of(new Socket(connectionInfo.getIp(), connectionInfo.getPort()));
            socketWriter = new PrintWriter(socket.get().getOutputStream());
            socketScanner = new Scanner(socket.get().getInputStream());
            sendToServer(Command.SET_NAME, playerName);
            initThreadReadingCommandsFromServer();
            startThreadReadingCommandsFromServer();
        } catch (IOException e) {
            logger.error(String.format(LogMessages.CANNOT_CONNECT_TO_SERVER, connectionInfo.getIp(), connectionInfo.getPort()));
        }
    }

    private void initThreadReadingCommandsFromServer() {
        final int breakTimeMillisBetweenReadingFromServer = 100;
        readCommandsFromUserThread = new Thread(() -> readFromServerUntilDisconnected(breakTimeMillisBetweenReadingFromServer));
    }

    private void readFromServerUntilDisconnected(int breakTimeMillisBetweenReadingFromServer) {
        while (isConnected()) {
            tryThreadSleep(breakTimeMillisBetweenReadingFromServer);
            logInfoIfAvailable();
        }
    }

    private void logInfoIfAvailable() {
        if (socketScanner.hasNextLine()) {
            logger.info(socketScanner.nextLine());
        }
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

    private void sendToServer(Command command) {
        sendToServer(command, "");
    }

    private void sendToServer(Command command, String commandValue) {
        if (isConnected()) {
            socketWriter.println(convertToProtocol(command, commandValue));
            socketWriter.flush();
        } else {
            logger.error(String.format(LogMessages.UNABLE_TO_SEND_MESSAGE, command));
        }
    }

    private String convertToProtocol(Command command, String commandValue) {
        return command + CommunicatingProtocol.getSeparator() + commandValue;
    }
}
