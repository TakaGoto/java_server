package com.server.Requests;

import com.server.Mocks.MockSocket;
import com.server.Responses.Router;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

public class RequestHandlerTest {
    MockSocket clientSocket = new MockSocket();
    Router router;
    RequestHandler reqHandler;

    @Before public void init() {
        String rootDir = "/Users/takayuki/Coding/java/cob_spec/public\n";
        router = new Router(rootDir);
        reqHandler = new RequestHandler(clientSocket, router);
    }

    @Test public void hasClientSocketAndRouter() {
        assertEquals(clientSocket, reqHandler.getClientSocket());
        assertEquals(router, reqHandler.getRouter());
    }

    @Test public void canRunARequest() throws IOException {
        reqHandler.run();
        assertEquals(ByteArrayInputStream.class, reqHandler.getClientSocket().getInputStream().getClass());
        assertEquals(1, clientSocket.closedCount);
    }
}
