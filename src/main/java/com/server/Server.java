package com.server;

import com.server.Handlers.Responder;
import com.server.Responses.Router;
import com.server.Sockets.IServerSockets;
import com.server.Sockets.MyServerSocket;


public class Server {
    protected IServerSockets serverSocket;
    protected Router router;
    private int port;
    ServerHandler serverHandler;

    public Server(int port) {
        this.port = port;
        setUpServer();
    }

    public void listen() {
        serverHandler = new ServerHandler(serverSocket, router);
        serverHandler.start();
    }

    public void setServerSocket(IServerSockets serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void mount(String URI, Responder responder) {
        router.addRoute(URI, responder);
    }

    private void setUpServer() {
        serverSocket = new MyServerSocket(port);
        router = new Router();
    }

    public int getPort() {
        return port;
    }
}
