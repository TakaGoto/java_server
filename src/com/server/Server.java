package com.server;

import com.server.Requests.MyRequestParser;
import com.server.Requests.Request;
import com.server.Requests.RequestParsers;
import com.server.Responses.Response;
import com.server.Responses.Router;
import com.server.Sockets.IServerSockets;
import com.server.Sockets.ISockets;
import com.server.Sockets.MyServerSocket;

import java.io.IOException;

public class Server {
    private IServerSockets serverSocket;
    private ISockets clientSocket;
    private RequestParsers parser;
    private Router router;
    private Request req;
    private Response resp = new Response();

    public Server(int port) {
        serverSocket = new MyServerSocket(port);
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

    public void setServerSocket(IServerSockets serverSocket) {
        this.serverSocket = serverSocket;
    }
}
