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
    private ExecutorService exec = Executors.newCachedThreadPool();
    RequestHandler reqHandler;

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
}
