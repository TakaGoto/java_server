package com.server.Requests;

import com.server.Responses.Response;
import com.server.Responses.Router;
import com.server.Sockets.ISockets;

import java.io.IOException;
import java.util.Hashtable;

public class RequestHandler implements Runnable {
    private IRequestParsers parser;
    private Response resp = new Response();
    private Request req;
    private Router router;
    private ISockets clientSocket;

    public RequestHandler(ISockets clientSocket, Router router) {
        this.clientSocket = clientSocket;
        this.router = router;
    }

    @Override public void run() {
        try {
            parser = new MyRequestParser(clientSocket.getInputStream());
            req = new Request(parser);
            Hashtable response = router.route(req.getReq());
            resp.writeTo(response, clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public ISockets getClientSocket() {
        return clientSocket;
    }

    public Router getRouter() {
        return router;
    }
}
