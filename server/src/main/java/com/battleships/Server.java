package com.battleships;

import com.battleships.Commands.AbstractCommand;
import com.battleships.Commands.CommandFactory;
import com.battleships.Commands.CommandsImpl.SetName;
import com.battleships.Messages.LogMessages;
import com.battleships.Player.ConnectedPlayers;
import com.battleships.Player.Player;
import com.battleships.commands.CommandType;
import com.battleships.commands.PlayerCommand;
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

        Thread infiniteLoopAcceptingPlayersThread = infiniteLoopAcceptingPlayers();
        infiniteLoopAcceptingPlayersThread.start();
    }

    private Thread infiniteLoopAcceptingPlayers() {
        return new Thread(() -> {
            while (true) {
                try {
                    Player newPlayer = acceptNewPlayer();
                    startHandlingPlayerCommandsLoop(newPlayer);
                } catch (IOException e) {
                    logger.error(LogMessages.PROBLEM_WHEN_ADDING_PLAYER + e.getMessage());
                }
            }
        });
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
        player.sendCommand(String.format(LogMessages.NICK_WAS_ASSIGNED_TO_YOU, player));
        registerPlayer(player);
        return player;
    }

    private Player acceptPlayer() throws IOException {
        Socket socket = serverSocket.accept();
        return Player.createForSocket(socket);
    }

    private void assignNameToNewUser(Player player) {
        PlayerCommand userRequest = player.nextCommand();
        if (userRequest.getCommandType() == CommandType.SET_NAME) {
            SetName setNameCommand = new SetName<>(userRequest.getValue(), connectedPlayers);
            setNameCommand.execute(player);
        }
        // TODO 16.07.2018 make sure command is SET_NAME - Damian
        // TODO 16.07.2018 refator that statement - Damian
    }

    private void registerPlayer(Player player) {
        connectedPlayers.add(player);
        player.sendCommand("Serwer wita: " + player);
    }

    private <V> void handlePlayerInput(Player player) {
        CommandType commandType = CommandType.START_PLAYING;
        while (!commandType.equals(CommandType.STOP_PLAYING)) {
            PlayerCommand<V> playerCommand = player.nextCommand();
            commandType = playerCommand.getCommandType();
            handlePlayerCommands(player, playerCommand);
        }
    }

    private <V> void handlePlayerCommands(Player player, PlayerCommand<V> playerCommand) {
        executePlayerCommand(player, playerCommand);

        //logging
        CommandType commandType = playerCommand.getCommandType();
        String commandValue = playerCommand.getValue().toString();
        logger.info(String.format(LogMessages.PLAYER_SENT_COMMAND, player, commandType, commandValue));
    }

    private <V> void executePlayerCommand(Player player, PlayerCommand<V> playerCommand) {
        AbstractCommand commandImpl = CommandFactory.getCommandImpl(playerCommand);
        commandImpl.execute(player);
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
