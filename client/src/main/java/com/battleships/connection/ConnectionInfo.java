package com.battleships.connection;

/**
 * Creates server's connection
 */

public class ConnectionInfo {
    private final String ip;
    private final int port;

    /**
     * Server connection's constructor
     *
     * @param ip - the ip address of the server
     * @param port - the port of the server
     */

    public ConnectionInfo(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    String getIp() {
        return ip;
    }

    int getPort() {
        return port;
    }
}
