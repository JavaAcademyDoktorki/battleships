package com.battleships.start_window.connection;

import com.battleships.commands.CommandType;
import com.battleships.LogMessages;
import com.battleships.commands.PlayerCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Optional;
import java.util.Scanner;

public enum Connection {
    INSTANCE;
    private Optional<Socket> socketOptional = Optional.empty();
    private ServerIO serverIO;
    private final static Logger logger = LogManager.getLogger(Connection.class);
    private Thread readCommandsFromUserThread;
    private static final int initialConnectingTimeout = 2000;


    public void establishConnection(ConnectionInfo connectionInfo) {
        if (!isConnected()) {
            tryToEstablishConnection(connectionInfo);
        }
    }

    private boolean isConnected() {
        return socketOptional.isPresent() && socketOptional.get().isConnected();
    }

    private void tryToEstablishConnection(ConnectionInfo connectionInfo) {
        try {
            socketOptional = Optional.of(new Socket());
            Socket socket = socketOptional.get();
            InetSocketAddress endpoint = new InetSocketAddress(connectionInfo.getIp(), connectionInfo.getPort());
            socket.connect(endpoint, initialConnectingTimeout);
        } catch (IOException e) {
            String errorMessage = String.format(LogMessages.CANNOT_CONNECT_TO_SERVER, connectionInfo.getIp(), connectionInfo.getPort());
            logger.error(errorMessage);
            //TODO message to user that problems occurred
        }
    }

    public void disconnect() {
        if (isConnected()) {
            tryToDisconnectFromServer();
        }
    }

    private void tryToDisconnectFromServer() {
        try {
            PlayerCommand<String> playerCommand = new PlayerCommand<>(CommandType.STOP_PLAYING, "");
            sendToServer(playerCommand);
            disconnectPlayer();
        } catch (IOException e) {
            logger.error(LogMessages.PROBLEM_WHEN_TRYING_TO_DISCONNECT + e.getMessage());
        } finally {
            socketOptional = Optional.empty();
        }
    }

    public <V> void sendToServer(PlayerCommand<V> playerCommand) {
        if (isConnected()) {
            serverIO.trySend(playerCommand);
            logger.info(String.format(LogMessages.COMMAND_SEND_SUCCEEDED, playerCommand.getCommandType()));
        } else {
            logger.error(String.format(LogMessages.COMMAND_SEND_FAILED, playerCommand.getCommandType()));
        }
    }

    private void disconnectPlayer() throws IOException {
        if (socketOptional.isPresent()) {
            socketOptional.get().close();
            logger.info(LogMessages.DISCONNECTED_AFTER_PLAYER_REQ_SUCCEED);
        } else {
            logger.error(LogMessages.DISCONNECTED_AFTER_PLAYER_REQ_FAILED);
        }
    }

    private void initThreadReadingCommandsFromServer() {
        final int breakTimeMillisBetweenReadingFromServer = 100;
        readCommandsFromUserThread = new Thread(() -> readFromServerUntilDisconnected(breakTimeMillisBetweenReadingFromServer));
    }

    private void startThreadReadingCommandsFromServer() {
        readCommandsFromUserThread.start();
    }

    private void readFromServerUntilDisconnected(int breakTimeMillisBetweenReadingFromServer) {
        while (isConnected()) {
            tryThreadSleep(breakTimeMillisBetweenReadingFromServer);
            logInfoFromServerIfAvailable();
        }
    }

    private void logInfoFromServerIfAvailable() {
        logger.info(serverIO.getMessageOptional());
    }

    private void tryThreadSleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void establishServerIO() throws IOException {
        Optional<OutputStream> outputStreamOptional = tryGetOutputStreamOptional();
        Optional<InputStream> inputStreamOptional = tryGetInputStreamOptional();

        if(outputStreamOptional.isPresent() && inputStreamOptional.isPresent()) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStreamOptional.get());
            Scanner socketScanner = new Scanner(inputStreamOptional.get());
            serverIO = new ServerIO(objectOutputStream, socketScanner);
        }

        initThreadReadingCommandsFromServer();
        startThreadReadingCommandsFromServer();
    }

    private Optional<OutputStream> tryGetOutputStreamOptional() throws IOException {
        Optional <OutputStream> outputStreamOptional = Optional.empty();
        try {
            if (socketOptional.isPresent()) {
                Socket socket = socketOptional.get();
                OutputStream socketOutputStream = socket.getOutputStream();
                outputStreamOptional = Optional.of(socketOutputStream);
            }
        } catch (IOException e) {
            logger.error(LogMessages.CANNOT_OBTAIN_SOCKET_OUTPUSTREAM);
            throw e;
        }
        return outputStreamOptional;
    }

    private Optional<InputStream> tryGetInputStreamOptional() throws IOException {
        Optional <InputStream> inputStreamOptional = Optional.empty();
        try {
            if (socketOptional.isPresent()) {
                Socket socket = socketOptional.get();
                InputStream socketInputStream = socket.getInputStream();
                inputStreamOptional = Optional.of(socketInputStream);
            }
        } catch (IOException e) {
            logger.error(LogMessages.CANNOT_OBTAIN_SOCKET_INPUTSTREAM);
            throw e;
        }
        return inputStreamOptional;
    }
}
