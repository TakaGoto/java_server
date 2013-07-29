package com.server.Mocks;

import com.server.Sockets.ServerSockets;
import com.server.Sockets.Sockets;

import java.io.IOException;

public class MockServerSocket implements ServerSockets{
    private int port;
    public int listenMax = 0;
    public boolean isClosed = false;
    public Sockets clientSocket = new MockSocket();

    public MockServerSocket(int port) {
        this.port = port;
    }

    public Sockets listen() throws IOException {
        listenMax++;
        isClosed = true;
        return clientSocket;
    }

    public int getPort() {
        return port;
    }

    public boolean notClosed() {
        return listenMax <= 0;
    }
}
