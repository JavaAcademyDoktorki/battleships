package com.battleships.connection;

import com.battleships.LogMessages;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.connection.commands.AbstractServerCommand;
import com.battleships.connection.commands.ServerCommandsFactory;
import com.battleships.startwindow.datainsertion.PlayerName;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Creates server's connection
 */
public enum Connection {
    INSTANCE;
    private Socket socket;
    private ServerIO serverIO;
    private final static Logger logger = LogManager.getLogger(Connection.class);
    private Thread readCommandsFromUserThread;
    private static final int INITIAL_CONNECTING_TIMEOUT = 2000;
    private final BooleanProperty connected = new SimpleBooleanProperty(false);
    private final BooleanProperty playerActive = new SimpleBooleanProperty(false);
    private final BooleanProperty playerReadyProperty = new SimpleBooleanProperty(false);
    private final PlayerName playerName = new PlayerName();

    public PlayerName getPlayerName() {
        return playerName;
    }

    public boolean getPlayerActive() {
        return playerActive.get();
    }

    public BooleanProperty playerActiveProperty() {
        return playerActive;
    }

    public void setPlayerActive(boolean playerActive) {
        this.playerActive.set(playerActive);
    }

    public void setPlayerReady(boolean playerReady) {
        this.playerReadyProperty.setValue(playerReady);
    }

    public BooleanProperty playerReadyProperty() {
        return this.playerReadyProperty;
    }

    /**
     * Establishes the server's connection
     *
     * @param connectionInfo - <code>ConnectionInfo</code> object that includes the ip and port of the server
     */

    public void establishConnection(ConnectionInfo connectionInfo) {
        if (!isConnected()) {
            tryToEstablishConnection(connectionInfo);
        }
    }

    public BooleanProperty connectedProperty() {
        return connected;
    }

    private void setConnected(boolean connected) {
        this.connected.set(connected);
    }

    public boolean isConnected() {
        checkConnected();
        return connected.get();
    }

    private void checkConnected() {
        setConnected(socket != null && socket.isConnected());
    }

    private void tryToEstablishConnection(ConnectionInfo connectionInfo) {
        try {
            socket = new Socket();
            InetSocketAddress endpoint = new InetSocketAddress(connectionInfo.getIp(), connectionInfo.getPort());
            socket.connect(endpoint, INITIAL_CONNECTING_TIMEOUT);
            establishServerIO();
        } catch (IOException e) {
            String errorMessage = String.format(LogMessages.CANNOT_CONNECT_TO_SERVER, connectionInfo.getIp(), connectionInfo.getPort());
            logger.error(errorMessage);
            //TODO playerName to user that problems occurred
        }
    }

    /**
     * Disconnects client from the server
     */

    public void disconnect() {
        if (isConnected()) {
            tryToDisconnectFromServer();
        }
    }

    private void tryToDisconnectFromServer() {
        try {
            Message message = new Message(CommandType.STOP_PLAYING, "");
            sendToServer(message);
            disconnectPlayer();
        } catch (IOException e) {
            logger.error(LogMessages.PROBLEM_WHEN_TRYING_TO_DISCONNECT + e.getMessage());
        } finally {
            socket = null;
        }
    }

    /**
     * Sends to the connected server specified command with command value
     *
     * @param message - <code>Message</code> Player command object with specified command kind and value
     */

    public void sendToServer(Message message) {
        if (isConnected()) {
            serverIO.trySend(message);
            logger.info(String.format(LogMessages.COMMAND_SEND_SUCCEEDED, message.getCommandType()));
        } else {
            logger.error(String.format(LogMessages.COMMAND_SEND_FAILED, message.getCommandType()));
        }
    }

    private void disconnectPlayer() throws IOException {
        if (socket != null) {
            socket.close();
            setConnected(false);
            logger.info(LogMessages.DISCONNECTED_AFTER_PLAYER_REQ_SUCCEED);
        } else {
            logger.error(LogMessages.DISCONNECTED_AFTER_PLAYER_REQ_FAILED);
        }
    }

    private void initThreadReadingCommandsFromServer() {
        readCommandsFromUserThread = new Thread(() ->
                readFromServerUntilDisconnected());
        readCommandsFromUserThread.setDaemon(true);
    }

    private void startThreadReadingCommandsFromServer() {
        readCommandsFromUserThread.start();
    }

    private void readFromServerUntilDisconnected() {
        Message message;
        while ((message = serverIO.getMessage()) != null) {
            AbstractServerCommand commandImpl = ServerCommandsFactory.getCommandImpl(message);
            commandImpl.execute();
            logInfoFromServerIfAvailable(message);
        }
    }

    private void logInfoFromServerIfAvailable(Message message) {
        logger.info(String.format("Klient odebrał komendę od serwera: %s. Wartość komendy: %s",
                message.getCommandType().toString(),
                message.getValue().toString()));
    }

    /**
     * Initializes input and output of the server client connected to
     */
    private void establishServerIO() {
        ObjectInputStream inputStream = getInputStream();
        ObjectOutputStream outputStream = getOutputStream();

        if (outputStream != null && inputStream != null) {
            serverIO = new ServerIO(outputStream, inputStream);
        } else {
            throw new RuntimeException("strumienie we/wy są nullami...");
        }

        initThreadReadingCommandsFromServer();
        startThreadReadingCommandsFromServer();
    }

    private ObjectOutputStream getOutputStream() {
        ObjectOutputStream outputStream = null;
        if (socket != null) {
            try {
                outputStream = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                logger.error(LogMessages.CANNOT_OBTAIN_SOCKET_OUTPUSTREAM);
                throw new RuntimeException(e);
            }
        }
        return outputStream;
    }

    private ObjectInputStream getInputStream() {
        ObjectInputStream inputStream = null;
        try {
            if (socket != null) {
                inputStream = new ObjectInputStream(socket.getInputStream());
            }
        } catch (IOException e) {
            logger.error(LogMessages.CANNOT_OBTAIN_SOCKET_INPUTSTREAM);
            throw new RuntimeException(e);
        }
        return inputStream;
    }
}
