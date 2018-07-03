package com.battleships;

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
        logger.info("Próba odpalenia serwera");
        this.serverSocket = new ServerSocket(50000);
        logger.info("Serwer odpalony... :)");
        connectedPlayers = new ConnectedPlayers();
        tryAcceptPlayersLoop();
    }

    private void tryAcceptPlayersLoop() {
        new Thread(() -> {
            while (true) {
                try {
                    addPlayerToTheGame();
                } catch (IOException e) {
                    logger.error("Wystąpił problem przy próbie dodania nowego gracza", e.getStackTrace());
                }
            }
        }).start();
    }


    private void addPlayerToTheGame() throws IOException {
        Player player = acceptPlayer();
        assignNameToNewUser(player);
        logger.info(String.format("Nowy gracz połączył się do serwera: %s", player));
        player.sendMessage(String.format("Dostałeś nick: %s", player));
        registerPlayer(player);
        new Thread(() -> handlePlayerMessagesUntilDisconnected(player)).start();
    }

    private Player acceptPlayer() throws IOException {
        Socket socket = serverSocket.accept();
        return Player.of(socket);
    }

    private void assignNameToNewUser(Player player) {
        String providedName = player.nextMessage();
        if (connectedPlayers.isNameAvailable(providedName) && !providedName.trim().equals("")) {
            player.setName(providedName);
        } else {
            player.setName(connectedPlayers.generateNewName());
        }
    }

    private void registerPlayer(Player player) {
        connectedPlayers.add(player);
        player.sendMessage("Serwer wita: " + player);
    }

    private void handlePlayerMessagesUntilDisconnected(Player player) {
        proceedWithPlayerMesseges(player);
        tryToDisconnectPlayer(player);
    }

    private void proceedWithPlayerMesseges(Player player) {
        // TODO THINK ABOUT A PROTOCOL...
        Commands command = Commands.GO_PLAYING;
        while (!command.equals(Commands.STOP_PLAYING)) {
            if (player.hasNextMessage()) {
                command = Commands.valueOf(player.nextMessage()); // TODO try send a object, or use some protocol here
                logger.info(String.format("Gracz %s wysłał komendę: %s", player, command));
            }
        }
    }

    private void tryToDisconnectPlayer(Player player) {
        try {
            disconnect(player);
        } catch (IOException e) {
            String logMessage = String.format("Nieudana próba rozłączenia gracza \"%s\" z serwerem", player);
            logger.info(logMessage, e.getMessage());
        }
    }

    private void disconnect(Player player) throws IOException {
        connectedPlayers.remove(player);
        player.disconnect();
        logger.info(String.format("Rozłączyłem gracza: %s", player));
    }
}
