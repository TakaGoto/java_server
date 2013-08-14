package com.server;

import com.server.Mocks.MockServerSocket;
import com.server.Responses.Router;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executors;

import static junit.framework.Assert.assertEquals;

public class ServerHandlerTest {
    ServerHandler serverHandler;
    MockServerSocket mockServerSocket;
    Router route;

    @Before public void init() {
        mockServerSocket = new MockServerSocket(5000);
        route = new Router("public/");
        serverHandler = new ServerHandler(mockServerSocket, route);
    }

    @Test public void serverHandlerHasExecutor() {
        assertEquals(Executors.newCachedThreadPool().getClass(), serverHandler.getExec().getClass());
    }

    @Test public void serverHandlerHasServerSocket() {
        assertEquals(mockServerSocket, serverHandler.getServerSocket());
    }

    @Test public void serverHandlerHasRouter() {
        assertEquals(route, serverHandler.getRouter());
    }
}
