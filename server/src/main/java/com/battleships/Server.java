package com.battleships;

import com.battleships.Commands.AbstractCommand;
import com.battleships.Commands.CommandFactory;
import com.battleships.Commands.CommandsImpl.SetName;
import com.battleships.Messages.LogMessages;
import com.battleships.Player.ConnectedPlayers;
import com.battleships.Player.Player;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.commands.OkCommandValue;
import com.battleships.commands.OkCommandObj;
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
//        player.sendCommand(String.format(LogMessages.NICK_WAS_ASSIGNED_TO_YOU, player));    // todo!
//        player.sendCommand(new Message<>(CommandType.SET_NAME, ""));
        registerPlayer(player);
        return player;
    }

    private Player acceptPlayer() throws IOException {
        Socket socket = serverSocket.accept();
        return Player.createForSocket(socket);
    }

    private void assignNameToNewUser(Player player) {
        Message userRequest = player.nextCommand();
        if (userRequest.getCommandType() == CommandType.SET_NAME) {
            SetName setNameCommand = new SetName<>(userRequest.getValue(), connectedPlayers);
            setNameCommand.execute(player);
        }
        // TODO 16.07.2018 make sure command is SET_NAME - Damian
        // TODO 16.07.2018 refator that statement - Damian
    }

    private void registerPlayer(Player player) {
        connectedPlayers.add(player);
        OkCommandObj okCommandObj = new OkCommandObj("To jest wiadomosc do wyswietlenia");
        OkCommandValue okCommandValue = new OkCommandValue(okCommandObj);
        player.sendCommand(new Message<>(CommandType.OK, okCommandValue));
//        player.sendCommand("Serwer wita: " + player);   // todo!
    }

    private <V> void handlePlayerInput(Player player) {
        CommandType commandType = CommandType.START_PLAYING;
        while (!commandType.equals(CommandType.STOP_PLAYING)) {
            Message<V> message = player.nextCommand();
            commandType = message.getCommandType();
            handlePlayerCommands(player, message);
        }
    }

    private <V> void handlePlayerCommands(Player player, Message<V> message) {
        executePlayerCommand(player, message);

        //logging
        CommandType commandType = message.getCommandType();
        String commandValue = message.getValue().toString();
        logger.info(String.format(LogMessages.PLAYER_SENT_COMMAND, player, commandType, commandValue));
        if (commandType == CommandType.SET_NAME) {
            player.sendCommand(new Message<>(CommandType.OK, "ok"));
        }
    }

    private <V> void executePlayerCommand(Player player, Message<V> message) {
        AbstractCommand commandImpl = CommandFactory.getCommandImpl(message);
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
