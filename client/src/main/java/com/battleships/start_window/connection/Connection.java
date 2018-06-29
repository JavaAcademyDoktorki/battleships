package com.battleships.start_window.connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public enum Connection {
    INSTANCE;

    private Socket socket;
    private PrintWriter socketWriter;
    private Scanner socketScanner;

    public boolean isConnected() {
        return socket != null && socket.isConnected();
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                sendToServer("quit");
                socket.close();
            } catch (IOException e) {
                // TODO obsluzyc
                e.printStackTrace();
            } finally {
                socket = null;
            }
        }
    }

    public void connect(ConnectInfo connectInfo, String name) {
        if (!isConnected()) {
            try {
                socket = new Socket(connectInfo.ip, connectInfo.port);
                socketWriter = new PrintWriter(socket.getOutputStream());
                socketScanner = new Scanner(socket.getInputStream());
                sendToServer(name);
            } catch (IOException e) {
                System.err.println(String.format("Couldnt connect to %s:%d", connectInfo.ip, connectInfo.port)); // TODO zalogować
                e.printStackTrace();
            }
        }
    }

    public void sendToServer(String message) {
        if (isConnected()) {
            socketWriter.println(message);
            socketWriter.flush();
        }
    }
}
