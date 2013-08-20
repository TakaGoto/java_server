package com.server;

import com.server.Requests.RequestHandler;
import com.server.Responses.Router;
import com.server.Sockets.IServerSockets;
import com.server.Sockets.ISockets;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerHandler {
    private IServerSockets serverSocket;
    private ISockets clientSocket;
    private Router router;
    private ExecutorService exec = Executors.newCachedThreadPool();
    RequestHandler reqHandler;

    public ServerHandler(IServerSockets serverSocket, Router router) {
        this.serverSocket = serverSocket;
        this.router = router;
    }

    public ExecutorService getExec() {
        return exec;
    }

    public IServerSockets getServerSocket() {
        return serverSocket;
    }

    public Router getRouter() {
        return router;
    }

    public void start() {
        try {
            while(serverSocket.notClosed()) {
                clientSocket = serverSocket.listen();
                reqHandler = new RequestHandler(clientSocket, router);
                exec.execute(reqHandler);
            }
        } catch(IOException e) {
            System.out.println("Accept failed: " + getPort());
        }
    }

    public int getPort() {
        return serverSocket.getPort();
    }
}
