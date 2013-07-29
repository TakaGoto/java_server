package com.server.Sockets;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

public class MyServerSocketTest {
    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    MyServerSocket serverSocket;

    @Before public void initialize() {
        System.setOut(new PrintStream(out));
    }

    @Test public void getAndSetServerPortNumber() {
        serverSocket = new MyServerSocket(5000);
        assertEquals(5000, serverSocket.getPort());
    }

    @Test public void getErrorMessageWithWrongPort() {
        serverSocket = new MyServerSocket(246);
        assertEquals("Cannot listen to port: 246\n", out.toString());
    }
}
