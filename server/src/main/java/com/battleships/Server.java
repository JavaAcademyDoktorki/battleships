package com.battleships;

import com.battleships.Commands.CommandFactory;
import com.battleships.Messages.LogMessages;
import com.battleships.commands.AbstractCommand;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.commands.values.PlayerRegisteredValue;
import com.battleships.player.ConnectedPlayers;
import com.battleships.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    private ServerSocket serverSocket;
    private static final Logger logger = LogManager.getLogger(Server.class.getName());
    private volatile int setupCount = 0;
    private volatile int roomCount = 0;
    private final int MAX_ROOMS = 2;
    public static final int SETUP_COMPLETED = 2;

    Server() {
    }

    public void start() throws IOException {
        logger.info(LogMessages.TRY_TO_RUN_SERVER);
        this.serverSocket = new ServerSocket(50000);
        logger.info(LogMessages.SERVER_RUNS);
        new Thread(() -> {
            while (true) {
                if (roomCount < MAX_ROOMS) {
                    roomCount++;
                    ConnectedPlayers connectedPlayers = new ConnectedPlayers();
                    infiniteLoopAcceptingPlayers(connectedPlayers); // todo ( na razie zapełnianie pokoju)
                }
            }
        }).start();
    }

    private void infiniteLoopAcceptingPlayers(ConnectedPlayers connectedPlayers) {
        while (connectedPlayers.notFull()) {
            try {
                acceptNewPlayer(connectedPlayers);
            } catch (IOException e) {
                logger.error(LogMessages.PROBLEM_WHEN_ADDING_PLAYER + e.getMessage());
            }
        }
        connectedPlayers.sendToActive(new Message(CommandType.START_PLAYING, true));  // todo (na razie rozgrywka tylko dwóch graczy)
        connectedPlayers.sendToInactive(new Message(CommandType.START_PLAYING, false));
    }

    private void startHandlingPlayerCommandsLoop(final Player newPlayer, ConnectedPlayers connectedPlayers) {
        new Thread(() -> handlePlayerCommandsUntilDisconnected(newPlayer, connectedPlayers)).start();
    }

    private void handlePlayerCommandsUntilDisconnected(Player newPlayer, ConnectedPlayers connectedPlayers) {
        handlePlayerInput(newPlayer, connectedPlayers);
        tryToDisconnectPlayer(newPlayer, connectedPlayers);
    }

    private void acceptNewPlayer(ConnectedPlayers connectedPlayers) throws IOException {
        Player player = acceptPlayer(connectedPlayers);
        assignNameToNewUser(player, connectedPlayers);
        logger.info(String.format(LogMessages.NEW_PLAYER_CONNECTED, player));
        registerPlayer(player, connectedPlayers);
        startHandlingPlayerCommandsLoop(player, connectedPlayers);
    }

    private Player acceptPlayer(ConnectedPlayers connectedPlayers) throws IOException {
        Socket socket = serverSocket.accept();
        return connectedPlayers.playerForSocket(socket);
    }

    private void assignNameToNewUser(Player player, ConnectedPlayers connectedPlayers) {
        Message userRequest = player.nextCommand();
        String name = (String) userRequest.getValue();
        if (usernameIsCorrect(name, connectedPlayers)) {
            player.setName(name);
            player.setPlayerNameSameAsGiven(true);
        } else {
            player.setName(connectedPlayers.generateNewName());
            player.setPlayerNameSameAsGiven(false);
        }
    }

    private boolean usernameIsCorrect(String name, ConnectedPlayers connectedPlayers) {
        return name != null && !name.equals("") && connectedPlayers.isNameAvailable(name.trim());
    }

    private void registerPlayer(Player player, ConnectedPlayers connectedPlayers) {
        connectedPlayers.add(player);
        PlayerRegisteredValue playerRegisteredValue =
                new PlayerRegisteredValue(player.getPlayerName(), player.isPlayerNameDifferentThanGiven());
        player.sendCommand(new Message(CommandType.PLAYER_REGISTERED_SUCCESSFULLY, playerRegisteredValue));
        logger.info(String.format("Komenda została wysłana do gracza: %s", CommandType.PLAYER_REGISTERED_SUCCESSFULLY.toString()));
    }

    private void handlePlayerInput(Player player, ConnectedPlayers connectedPlayers) {
        CommandType commandType = CommandType.START_PLAYING;
        while (!commandType.equals(CommandType.STOP_PLAYING)) {
            Message message = player.nextCommand();
            commandType = message.getCommandType();
            handlePlayerCommands(player, message, connectedPlayers);

            sendYouAreReadyMessage(player, commandType, message, connectedPlayers);
        }
    }

    private void sendYouAreReadyMessage(Player player, CommandType commandType, Message message, ConnectedPlayers connectedPlayers) {
        if (commandType.equals(CommandType.SETUP_COMPLETED)) {
            setupCount++;
        }
        if (setupCount == SETUP_COMPLETED) {
            connectedPlayers.sendToActive(new Message(CommandType.YOU_ARE_READY, true));
            logger.info(String.format(LogMessages.PLAYER_SENT_COMMAND, player, commandType, message.getValue()));
            setupCount = 0;
        }
    }

    private void handlePlayerCommands(Player player, Message message, ConnectedPlayers connectedPlayers) {
        executePlayerCommand(message, connectedPlayers);
        //logging
        CommandType commandType = message.getCommandType();
        String commandValue = message.getValue().toString();
        logger.info(String.format(LogMessages.PLAYER_SENT_COMMAND, player, commandType, commandValue));
    }

    private void executePlayerCommand(Message message, ConnectedPlayers connectedPlayers) {
        AbstractCommand commandImpl = CommandFactory.getCommandImpl(message);
        commandImpl.execute(connectedPlayers);
    }

    private void tryToDisconnectPlayer(Player player, ConnectedPlayers connectedPlayers) {
        try {
            disconnect(player, connectedPlayers);
        } catch (IOException e) {
            String logMessage = String.format(LogMessages.UNSUCCESSFUL_TRY_TO_DISCONNECT_PLAYER, player);
            logger.info(logMessage, e.getMessage());
        }
    }

    private void disconnect(Player player, ConnectedPlayers connectedPlayers) throws IOException {
        connectedPlayers.remove(player);
        player.disconnect();
        if (connectedPlayers.areThereAnyPlayer()) {
            connectedPlayers.sendToActive(new Message(CommandType.SHUT_DOWN, "room orphaned"));
            roomCount--;
        }
        logger.info(String.format(LogMessages.DISCONNECT_PLAYER, player));
        System.out.println("CURRENT ROOM COUNT " + roomCount);
    }
}
