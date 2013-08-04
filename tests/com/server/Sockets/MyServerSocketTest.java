package com.server.Sockets;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

public class MyServerSocketTest {
    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    MyServerSocket serverSocket;

    @Before public void initialize() {
        System.setOut(new PrintStream(out));
    }

    @Test public void getAndSetServerPortNumber() throws IOException {
        serverSocket = new MyServerSocket(5001);
        assertEquals(5001, serverSocket.getPort());
        serverSocket.close();
    }

    @Test public void getErrorMessageWithWrongPort() {
        serverSocket = new MyServerSocket(246);
        assertEquals("Cannot listen to port: 246\n", out.toString());
    }

    @Test public void testServerIsClosed() throws IOException {
        serverSocket = new MyServerSocket(5001);
        assertEquals(true, serverSocket.notClosed());
        serverSocket.close();
    }
}
