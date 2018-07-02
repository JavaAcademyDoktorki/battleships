package com.battleships.start_window.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public enum Connection {
    INSTANCE;

    private Socket socket;
    private PrintWriter socketWriter;
    private Scanner socketScanner;
    private final static Logger logger = LogManager.getLogger(Connection.class);

    public void disconnect() {
        if (isConnected()) {
            try {
                sendToServer("quit");
                socket.close();
                logger.error("Rozłączony z serwerem na żądanie gracza");
            } catch (IOException e) {
               logger.error("Problem z rozłączeniem serwera: " + e.getMessage());
            } finally {
                socket = null;
            }
        }
    }

    private boolean isConnected() {
        return socket != null && socket.isConnected();
    }

    public void connect(ConnectInfo connectInfo, String name) {
        if (!isConnected()) {
            try {
                socket = new Socket(connectInfo.ip, connectInfo.port);
                socketWriter = new PrintWriter(socket.getOutputStream());
                socketScanner = new Scanner(socket.getInputStream());
                sendToServer(name);
                initReadingMessagesFromServer();
            } catch (IOException e) {
                logger.error(String.format("Nie można podłączyć do serwera: %s:%d", connectInfo.ip, connectInfo.port));
            }
        }
    }

    private void initReadingMessagesFromServer() {
        new Thread(() ->{
            tryThreadSleep(100);
            if (socketScanner.hasNextLine()){
                logger.info(socketScanner.nextLine());
            }
        }).start();
    }

    private void tryThreadSleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendToServer(String message) {
        if (isConnected()) {
            socketWriter.println(message);
            socketWriter.flush();
        }
    }
}
