package com.battleships.start_window.connection;

import com.battleships.Command;
import com.battleships.CommunicatingProtocol;
import com.battleships.LogMessages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Optional;
import java.util.Scanner;

public enum Connection {
    INSTANCE;
    private Optional<Socket> socket = Optional.empty();
    private ServerIO serverIO;
    private final static Logger logger = LogManager.getLogger(Connection.class);
    private Thread readCommandsFromUserThread;
    private static final int initialConnectingTimeout = 2000;

    public void disconnect() {
        if (isConnected()) {
            tryToDisconnectFromServer();
        }
    }

    private void tryToDisconnectFromServer() {
        try {
            sendToServer(Command.STOP_PLAYING);
            disconnectPlayer();
        } catch (IOException e) {
            logger.error(LogMessages.PROBLEM_WHEN_TRYING_TO_DISCONNECT + e.getMessage());
        } finally {
            socket = Optional.empty();
        }
    }

    private void disconnectPlayer() throws IOException {
        if (socket.isPresent()) {
            socket.get().close();
            logger.info(LogMessages.DISCONNECTED_AFTER_PLAYER_REQ_SUCCEED);
        } else {
            logger.error(LogMessages.DISCONNECTED_AFTER_PLAYER_REQ_FAILED);
        }
    }

    private boolean isConnected() {
        return socket.isPresent() && socket.get().isConnected();
    }

    public void establishConnection(ConnectionInfo connectionInfo) {
        if (!isConnected()) {
            tryToEstablishConnection(connectionInfo);
        }
    }

    private void tryToEstablishConnection(ConnectionInfo connectionInfo) {
        try {
            socket = Optional.of(new Socket());
            socket.get().connect(new InetSocketAddress(connectionInfo.getIp(), connectionInfo.getPort()), initialConnectingTimeout);
        } catch (IOException e) {
            logger.error(String.format(LogMessages.CANNOT_CONNECT_TO_SERVER, connectionInfo.getIp(), connectionInfo.getPort()));
            //TODO message to user that problems occurred
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

    private void sendToServer(Command command) {
        sendToServer(command, "");
    }

    public void sendToServer(Command command, String commandValue) {
        if (isConnected()) {
            serverIO.send(convertToProtocol(command, commandValue));
            logger.info(String.format(LogMessages.COMMAND_SEND_SUCCEEDED, command));
        } else {
            logger.error(String.format(LogMessages.COMMAND_SEND_FAILED, command));
        }
    }

    private String convertToProtocol(Command command, String commandValue) {
        return command + CommunicatingProtocol.getSeparator() + commandValue;
    }

    public void establishServerIO() {
        Optional<OutputStream> outputStreamOptional = getOutputStreamOptional();
        Optional<InputStream> inputStreamOptional = getInputStreamOptional();

        if(outputStreamOptional.isPresent() && inputStreamOptional.isPresent()) {
            PrintWriter socketWriter = new PrintWriter(outputStreamOptional.get());
            Scanner socketScanner = new Scanner(inputStreamOptional.get());
            serverIO = new ServerIO(socketWriter, socketScanner);
        } else {
            // TODO do not allow to go forward with application when here...
        }
        initThreadReadingCommandsFromServer();
        startThreadReadingCommandsFromServer();
    }

    private Optional<OutputStream> getOutputStreamOptional() {
        Optional <OutputStream> outputStreamOptional = Optional.empty();
        try {
            if (socket.isPresent()) {
                outputStreamOptional = Optional.of(socket.get().getOutputStream());
            }
        } catch (IOException e) {
            logger.error(LogMessages.CANNOT_OBTAIN_SOCKET_OUTPUSTREAM);
        }
        return outputStreamOptional;
    }

    private Optional<InputStream> getInputStreamOptional() {
        Optional <InputStream> inputStreamOptional = Optional.empty();
        try {
            if (socket.isPresent()) {
                inputStreamOptional = Optional.of(socket.get().getInputStream());
            }
        } catch (IOException e) {
            logger.error(LogMessages.CANNOT_OBTAIN_SOCKET_INPUTSTREAM);
        }
        return inputStreamOptional;
    }
}
