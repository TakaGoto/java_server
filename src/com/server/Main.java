package com.server;

import com.server.Sockets.MyServerSocket;
import com.server.Sockets.ServerSockets;

public class Main {

    public static void main(String[] args) {
        ServerSockets serverSockets = new MyServerSocket(5000);
        Server server = new Server(serverSockets);
        server.listen();
    }
}
