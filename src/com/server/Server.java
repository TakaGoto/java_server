package com.server;

import com.server.Handlers.Responder;
import com.server.Responses.Router;
import com.server.Sockets.IServerSockets;
import com.server.Sockets.MyServerSocket;


public class Server {
    private IServerSockets serverSocket;
    protected Router router;
    private int port;
    private String rootDir;
    ServerHandler serverHandler;

    public Server(int port, String rootDir) {
        this.rootDir = rootDir;
        this.port = port;
        setUpServer();
    }

    public Router getRouter() {
        return router;
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
        router = new Router(rootDir);
    }

    public int getPort() {
        return port;
    }
}
