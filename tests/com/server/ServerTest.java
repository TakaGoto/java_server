package com.server;

import com.server.Mocks.MockServerSocket;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ServerTest {
    MockServerSocket mockServerSocket;
    Server server;

    @Before public void initialize() {
        mockServerSocket = new MockServerSocket(5000);
        server = new Server(mockServerSocket);
    }
    @Test public void constructorGetsServerSocket() {
        assertEquals(5000, server.getPort());
    }

    @Test public void serverListensToPort() {
        server.listen();
        assertEquals(true, mockServerSocket.isClosed);
    }

    @Test public void serverAttemptsListeningToPort() {
        server.listen();
        assertEquals(1, mockServerSocket.listenMax);
    }
}

