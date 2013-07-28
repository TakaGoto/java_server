package com.server;

import java.io.IOException;
import java.net.ServerSocket;

public class httpServerSocket {
    private ServerSocket serverSocket;
    private int port;

    public httpServerSocket(int port) {
        this.port = port;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Cannot listen to port: " + port);
        }
    }

    public httpServerSocket() {
        try {
            serverSocket = new ServerSocket(0, 128);
            port = serverSocket.getLocalPort();
        } catch (IOException e) {
            System.out.println("Cannot listen to port: " + port);
        }
    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }
}
