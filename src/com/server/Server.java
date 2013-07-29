package com.server;

import com.server.Sockets.ServerSockets;
import com.server.Sockets.Sockets;

import java.io.IOException;

public class Server {
    ServerSockets serverSocket;
    Sockets clientSocket;

    public Server(ServerSockets serverSocket) {
        this.serverSocket = serverSocket;
    }

    public int getPort() {
        return serverSocket.getPort();
    }

    public void listen() {
        try {
            while(serverSocket.notClosed()) {
                clientSocket = serverSocket.listen();
                clientSocket.close();
            }
        } catch(IOException e) {
            System.out.println("Accept failed: " + getPort());
        }
    }
}
