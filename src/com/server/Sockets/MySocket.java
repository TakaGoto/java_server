package com.server.Sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MySocket implements Sockets {
    Socket clientSocket;

    public MySocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void close() throws IOException {
        clientSocket.close();
    }

    public OutputStream getOutputStream() throws IOException {
        return clientSocket.getOutputStream();
    }

    public InputStream getInputStream() throws IOException {
        return clientSocket.getInputStream();
    }
}
