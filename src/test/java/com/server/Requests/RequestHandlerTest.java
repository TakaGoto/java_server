package com.server.Requests;

import com.server.Mocks.MockSocket;
import com.server.Responses.Router;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public class RequestHandlerTest {
    MockSocket serverSocket = new MockSocket();
    Router router;
    RequestHandler reqHandler;

    @Before public void init() {
        router = new Router();
        reqHandler = new RequestHandler(serverSocket, router);
    }

    @Test public void hasClientSocketAndRouter() {
        assertEquals(serverSocket, reqHandler.getClientSocket());
        assertEquals(router, reqHandler.getRouter());
    }

    @Test public void canRunARequest() throws IOException {
        reqHandler.run();
        assertEquals(1, serverSocket.closedCount);
    }
}
