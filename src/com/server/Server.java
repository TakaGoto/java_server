package com.server;

import com.server.Requests.RequestHandler;
import com.server.Responses.Router;
import com.server.Sockets.IServerSockets;
import com.server.Sockets.ISockets;
import com.server.Sockets.MyServerSocket;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private IServerSockets serverSocket;
    private ISockets clientSocket;
    private Router router;
    private int port;
    private String rootDir;
    private ExecutorService exec = Executors.newCachedThreadPool();
    RequestHandler reqHandler;

    public Server(int port, String rootDir) {
        this.rootDir = rootDir;
        this.port = port;
        serverSocket = new MyServerSocket(port);
        router = new Router(rootDir);
    }

    public int getPort() {
        return serverSocket.getPort();
    }

    public Router getRouter() {
        return router;
    }

    public void listen() {
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

    public void setServerSocket(IServerSockets serverSocket) {
        this.serverSocket = serverSocket;
    }

    public ExecutorService getExec() {
        return exec;
    }
}
