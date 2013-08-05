package com.server;

import com.server.Mocks.MockServerSocket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

public class ServerTest {
    MockServerSocket mockServerSocket;
    Server server;

    @Before public void initialize() {
        String rootDir = "/Users/takayuki/Coding/java/cob_spec/public";

        mockServerSocket = new MockServerSocket(5000);
        server = new Server(5000,rootDir );
        server.setServerSocket(mockServerSocket);
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
        assertEquals(4, mockServerSocket.listenMax);
    }

    @Test public void serverIOException() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        server.listen();
        assertEquals(4, mockServerSocket.listenMax);
        assertEquals(" ", out.toString());
    }

    @Test public void serverTakesInRouterDirectory() {
        server = new Server(5000, "/public");
        assertEquals("/public", server.getRouter().getDir());
    }
}

