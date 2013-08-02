package com.server;

import com.server.Requests.MyRequestParser;
import com.server.Requests.Request;
import com.server.Requests.RequestParsers;
import com.server.Responses.Response;
import com.server.Sockets.ServerSockets;
import com.server.Sockets.Sockets;

import java.io.IOException;
import java.io.InputStreamReader;

public class Server {
    private ServerSockets serverSocket;
    private Sockets clientSocket;
    private RequestParsers parser;
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
                parser = new MyRequestParser(clientSocket.getInputStream());
                req = new Request(parser);
                resp.writeTo(router.route(req.getReq()), clientSocket.getOutputStream());
                clientSocket.close();
            }
        } catch(IOException e) {
            System.out.println("Accept failed: " + getPort());
        }
    }
}
