package com.battleships;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    private final ServerSocket serverSocket;
    private final ConnectedPlayers connectedPlayers;
    private static Logger logger = LogManager.getLogger(Server.class.getName());

    Server() throws IOException {
        logger.info("próba odpalenia serwera");
        this.serverSocket = new ServerSocket(50000);
        logger.info("serwer odpalony i działa... :)");
        connectedPlayers = new ConnectedPlayers();
        tryAcceptPlayersLoop();
    }

    private void tryAcceptPlayersLoop() {
        new Thread(() -> {
            while (true) {
                try {
                    acceptPlayer();
                } catch (IOException e) {
                    logger.error(e.getStackTrace());
                }
            }
        }).start();
    }

    private Thread thread;
    private void acceptPlayer() throws IOException {
        Socket socket = serverSocket.accept();
        Player player = Player.of(socket);
        player.setName(player.nextMessage()); // todo hasNextMessage?
        logger.info(String.format("Nowy gracz połączył się do serwera: %s", player));
        registerPlayer(player);
        thread = new Thread(() -> handlePlayerMessagesUntilClose(player));
        thread.start();
    }

    private void registerPlayer(Player player) {
        connectedPlayers.add(player);
        player.sendMessage("Serwer wita: " + player); // TODO not working ?
    }

    private void handlePlayerMessagesUntilClose(Player player) {
        proceedWithPlayerMesseges(player);
        tryToDisconnectPlayer(player);
    }

    private void proceedWithPlayerMesseges(Player player) {
        String command = "";
        while (!"quit".equalsIgnoreCase(command)) {
            if (player.hasNextMessage()) {
                command = player.nextMessage();
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
        logger.info(String.format("Gracz rozłączył się z serwerem: %s", player));
    }
}
