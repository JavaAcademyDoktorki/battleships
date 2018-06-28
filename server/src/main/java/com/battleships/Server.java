package com.battleships;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private final ServerSocket serverSocket;
    private final List<Client> clients;
    private static Logger logger = Logger.getLogger(Server.class);

    public Server() throws IOException {
        logger.info("attempt to start server");
        this.serverSocket = new ServerSocket(50000);
        logger.info("server up and running...");
        clients = new CopyOnWriteArrayList<>();
        tryAcceptClientsLoop();
    }

    private void tryAcceptClientsLoop() {
        new Thread(() -> {
            while (true) {
                try {
                    acceptClients();
                } catch (IOException e) {
                    logger.error(e.getStackTrace());
                }
            }
        }).start();
    }

    private void acceptClients() throws IOException {
        Socket socket = serverSocket.accept();
        Client client = Client.of(socket);
        logger.info(String.format("registration of user: %d", clients.size() + 1));
        registerCLient(client);
        new Thread(() -> handleClients(client)).start();
    }

    private void handleClients(Client client) {
        String command = "";
        while (!"quit".equalsIgnoreCase(command)) {
            if (client.hasNextMessage()) {
                command = client.getNextMessage();
                logger.info(command);
            }
        }
        try {
            disconnect(client);
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }

    private void disconnect(Client client) throws IOException {
        clients.remove(client);
        client.disconnect();
        logger.info("client disconnected");
    }

    private void registerCLient(Client client) {
        clients.add(client);
        client.writeMessage("hello from server");
    }
}
