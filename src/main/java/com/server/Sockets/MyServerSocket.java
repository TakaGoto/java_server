package com.server.Sockets;

import java.io.IOException;
import java.net.ServerSocket;

public class MyServerSocket implements IServerSockets{
    ServerSocket serverSocket;

    public MyServerSocket(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Cannot listen to port: " + port);
        }
    }

    public ISockets listen() throws IOException {
        return new MySocket(serverSocket.accept());
    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    public boolean notClosed() {
        return !serverSocket.isClosed();
    }

    public void close() throws IOException {
        serverSocket.close();
    }
}
