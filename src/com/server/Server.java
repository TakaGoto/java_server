package com.server;

import com.server.Handlers.Request;
import com.server.Handlers.Response;
import com.server.Sockets.ServerSockets;
import com.server.Sockets.Sockets;

import java.io.IOException;

public class Server {
    private ServerSockets serverSocket;
    private Sockets clientSocket;
    private Router router;
    private Request req;
    private Response resp = new Response();

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
                req = new Request(clientSocket.getInputStream());
                resp.writeTo(router.route(req.getReq()), clientSocket.getOutputStream());
                clientSocket.close();
            }
        } catch(IOException e) {
            System.out.println("Accept failed: " + getPort());
        }
    }
}
