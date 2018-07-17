package com.battleships;

import com.battleships.Messages.LogMessages;
import com.battleships.commands.CommandType;
import com.battleships.commands.MessageTranslator;
import com.battleships.commands.Values.PlayerRegisteredValue;
import com.battleships.player.ConnectedPlayers;
import com.battleships.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    private final ServerSocket serverSocket;
    private final ConnectedPlayers connectedPlayers;
    private static final Logger logger = LogManager.getLogger(Server.class.getName());

    Server() throws IOException {
        logger.info(LogMessages.TRY_TO_RUN_SERVER);
        this.serverSocket = new ServerSocket(50000);
        logger.info(LogMessages.SERVER_RUNS);
        this.connectedPlayers = new ConnectedPlayers();
        infiniteLoopAcceptingPlayers(); // todo ( na razie zapełnianie pokoju)
        new Thread(() -> {  // todo (na razie rozgrywka tylko dwóch graczy)
            connectedPlayers.sendToActive(MessageTranslator.prepareMessage(CommandType.START_PLAYING, true));
            connectedPlayers.sendToInactive(MessageTranslator.prepareMessage(CommandType.START_PLAYING, false));
        }).start();

    }

    private void infiniteLoopAcceptingPlayers() {
        while (connectedPlayers.notFull()) {
            try {
                Player newPlayer = acceptNewPlayer();
                startHandlingPlayerCommandsLoop(newPlayer);
            } catch (IOException e) {
                logger.error(LogMessages.PROBLEM_WHEN_ADDING_PLAYER + e.getMessage());
            }
        }
    }

    private void startHandlingPlayerCommandsLoop(final Player newPlayer) {
        new Thread(() -> handlePlayerCommandsUntilDisconnected(newPlayer)).start();
    }

    private void handlePlayerCommandsUntilDisconnected(Player newPlayer) {
        handlePlayerInput(newPlayer);
        tryToDisconnectPlayer(newPlayer);
    }

    private Player acceptNewPlayer() throws IOException {
        Player player = acceptPlayer();
        assignNameToNewUser(player);
        logger.info(String.format(LogMessages.NEW_PLAYER_CONNECTED, player));
        registerPlayer(player);
        return player;
    }

    private Player acceptPlayer() throws IOException {
        Socket socket = serverSocket.accept();
        return connectedPlayers.playerForSocket(socket);
    }

    private void assignNameToNewUser(Player player) {
        String userRequest = player.nextCommand();
        String name = MessageTranslator.getMessageContent(userRequest);
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

    private void registerPlayer(Player player) {
        connectedPlayers.add(player);
        PlayerRegisteredValue playerRegisteredValue =
                new PlayerRegisteredValue(player.getPlayerName(), player.isPlayerNameDifferentThanGiven());
        player.sendCommand(MessageTranslator.prepareMessage(CommandType.PLAYER_REGISTERED_SUCCESSFULLY, playerRegisteredValue));
        logger.info(String.format("Komenda została wysłana do gracza: %s", CommandType.PLAYER_REGISTERED_SUCCESSFULLY.toString()));
    }

    private <V> void handlePlayerInput(Player player) {
        CommandType commandType = CommandType.START_PLAYING;
        while (!commandType.equals(CommandType.STOP_PLAYING)) {
            String message = player.nextCommand();
            commandType = MessageTranslator.getCommandTypeFromMessage(message);
            handlePlayerCommands(player, message);
        }
    }

    private void handlePlayerCommands(Player player, String message) {
        //logging
        CommandType commandType = MessageTranslator.getCommandTypeFromMessage(message);
        String commandValue = MessageTranslator.getMessageContent(message);
        logger.info(String.format(LogMessages.PLAYER_SENT_COMMAND, player, commandType, commandValue));
    }

    private void tryToDisconnectPlayer(Player player) {
        try {
            disconnect(player);
        } catch (IOException e) {
            String logMessage = String.format(LogMessages.UNSUCCESSFUL_TRY_TO_DISCONNECT_PLAYER, player);
            logger.info(logMessage, e.getMessage());
        }
    }

    private void disconnect(Player player) throws IOException {
        connectedPlayers.remove(player);
        player.disconnect();
        logger.info(String.format(LogMessages.DISCONNECT_PLAYER, player));
    }
}
