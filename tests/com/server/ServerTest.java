package com.server;


import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static junit.framework.Assert.assertEquals;

public class ServerTest {
    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    httpServerSocket serverSocket;

    @Before public void initialize() {
        System.setOut(new PrintStream(out));
    }
    @Test public void getAndSetServerPortNumber() throws IOException {
        serverSocket = new httpServerSocket(5000);
        assertEquals(5000, serverSocket.getPort());
    }

    @Test public void getDefaultServerPortNumber() {
        serverSocket = new httpServerSocket();
        Integer port = serverSocket.getPort();
        assertEquals(Integer.class, port.getClass());
    }

    @Test public void getErrorMessageWithWrongPort() {
        serverSocket = new httpServerSocket(246);
        assertEquals("Cannot listen to port: 246\n", out.toString());
    }
}

