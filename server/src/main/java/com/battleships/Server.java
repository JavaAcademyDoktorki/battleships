package com.battleships;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    private final ServerSocket serverSocket;
    private final ConnectedPlayers connectedPlayers;
    private static Logger logger = Logger.getLogger(Server.class);

    Server() throws IOException {
        logger.info("attempt to stlart server");
        this.serverSocket = new ServerSocket(50000);
        logger.info("server up and running...");
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

    private void acceptPlayer() throws IOException {
        Socket socket = serverSocket.accept();
        Player player = Player.of(socket);
        player.setName(player.getNextMessage());
        logger.info(String.format("Registration of user: %s", player));
        registerPlayer(player);
        new Thread(() -> handlePlayers(player)).start();
    }

    private void registerPlayer(Player player) {
        connectedPlayers.add(player);
        String name = player.getNextMessage();
        player.sendMessage("hello from server: " + name);
    }

    private void handlePlayers(Player player) {
        String command = "";
        while (!"quit".equalsIgnoreCase(command)) {
            if (player.hasNextMessage()) {
                command = player.getNextMessage();
                logger.info(command);
            }
        }
        try {
            disconnect(player);
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }

    private void disconnect(Player player) throws IOException {
        connectedPlayers.remove(player);
        player.disconnect();
        logger.info("player disconnected");
    }
}
