package com.server;

import com.server.Handlers.RequestParser;
import com.server.Handlers.Response;
import com.server.Handlers.Responses;
import com.server.Sockets.ServerSockets;
import com.server.Sockets.Sockets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Hashtable;

public class Server {
    ServerSockets serverSocket;
    Sockets clientSocket;
    Router router;
    RequestParser req = new RequestParser();
    Response resp = new Response();

    public Server(ServerSockets serverSocket) {
        this.serverSocket = serverSocket;
        router = new Router();
    }

    public int getPort() {
        return serverSocket.getPort();
    }

    public void listen() {
        try {
            while(serverSocket.notClosed()) {
                clientSocket = serverSocket.listen();
                Hashtable<String, String> request = req.parseHeader(clientSocket.getInputStream());
                resp.write(router.route(request), clientSocket.getOutputStream());
                clientSocket.close();
            }
        } catch(IOException e) {
            System.out.println("Accept failed: " + getPort());
        }
    }
}
