package com.battleships;

import com.battleships.Messages.LogMessages;
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
        connectedPlayers = new ConnectedPlayers();
        tryAcceptPlayersLoop();
    }

    private void tryAcceptPlayersLoop() {
        new Thread(() -> {
            while (true) {
                try {
                    addPlayerToTheGame();
                    handlePlayerInputUntillDisconnect();
                } catch (IOException e) {
                    logger.error(LogMessages.PROBLEM_WHEN_ADDING_PLAYER, e.getStackTrace());
                }
            }
        }).start();
    }

    private void handlePlayerInputUntillDisconnect() {
        new Thread(() -> handlePlayerCommandsUntilDisconnected(connectedPlayers.getLastAddedPlayer())).start();
    }


    private void addPlayerToTheGame() throws IOException {
        Player player = acceptPlayer();
        assignNameToNewUser(player);
        logger.info(String.format(LogMessages.NEW_PLAYER_CONNECTED, player));
        player.sendCommand(String.format(LogMessages.NICK_WAS_ASSIGNED_TO_YOU, player));
        registerPlayer(player);
    }

    private Player acceptPlayer() throws IOException {
        Socket socket = serverSocket.accept();
        return Player.createForSocket(socket);
    }

    private void assignNameToNewUser(Player player) {
        String providedName = player.nextCommand();
        if (connectedPlayers.isNameAvailable(providedName) && !providedName.trim().equals("")) {
            player.setName(providedName);
        } else {
            player.setName(connectedPlayers.generateNewName());
        }
    }

    private void registerPlayer(Player player) {
        connectedPlayers.add(player);
        player.sendCommand("Serwer wita: " + player); // TODO protocol to send commands
    }

    private void handlePlayerCommandsUntilDisconnected(Player player) {
        handlePlayerInput(player);
        tryToDisconnectPlayer(player);
    }

    private void handlePlayerInput(Player player) {
        // TODO THINK ABOUT A PROTOCOL...
        Commands command = Commands.GO_PLAYING;
        while (!command.equals(Commands.STOP_PLAYING)) {
            if (player.hasNextCommand()) {
                command = Commands.valueOf(player.nextCommand()); // TODO try send a object, or use some protocol here
                logger.info(String.format(LogMessages.PLAYER_SENT_COMMAND, player, command));
            }
        }
    }

    private void tryToDisconnectPlayer(Player player) {
        try {
            disconnect(player);
        } catch (IOException e) {
            String logMessage = String.format(LogMessages.UNSUCCESFUL_TRY_TO_DISCONNECT_PLAYER, player);
            logger.info(logMessage, e.getMessage());
        }
    }

    private void disconnect(Player player) throws IOException {
        connectedPlayers.remove(player);
        player.disconnect();
        logger.info(String.format(LogMessages.DISCONNECT_PLAYER, player));
    }
}
