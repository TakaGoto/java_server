package com.server.Mocks;

import com.server.Sockets.IServerSockets;
import com.server.Sockets.ISockets;

import java.io.IOException;

public class MockServerSocket implements IServerSockets{
    private int port;
    public int listenMax = 0;
    public boolean isClosed = false;
    public ISockets clientSocket = new MockSocket();

    public MockServerSocket(int port) {
        this.port = port;
    }

    public ISockets listen() {
        try {
            while(listenMax < 4) listenMax++;
            isClosed = true;
            if(listenMax == 4) throw new IOException();
        } catch (IOException e) {
            System.out.print(" ");
        }
        return clientSocket;
    }

    public int getPort() {
        return port;
    }

    public boolean notClosed() {
        return listenMax <= 0;
    }

    @Override
    public void close() throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
