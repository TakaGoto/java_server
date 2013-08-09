package com.server;

import com.server.Mocks.MockRequestHandler;
import com.server.Mocks.MockServerSocket;
import com.server.Mocks.MockSocket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class ServerTest {
    MockServerSocket mockServerSocket;
    MockRequestHandler handler;
    Server server;
    String rootDir;

    @Before public void initialize() {
        rootDir = "/Users/takayuki/Coding/java/cob_spec/public";

        handler = new MockRequestHandler();
        mockServerSocket = new MockServerSocket(5000);
        server = new Server(5000, rootDir);
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

    @Test public void socketIOException() {
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

    @Test public void serverTakesInMultipleClientSocketsRequests() throws IOException {
        server.listen();

        attemptMultipleConnect();
        server.getExec().shutdownNow();

        assertEquals(4, mockServerSocket.listenMax);
    }

//    @Test public void parseArgs() {
//        String[] args = new String[]{"2"};
//        Server newServer = new Server(5000, "/public", args);
//        newServer.setServerSocket(mockServerSocket);
//        assertEquals(5000, newServer.getPort());
//    }

    private void attemptMultipleConnect() throws IOException {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    int count = 0;
                    while(count != 4) {
                        Socket clientSocket = new Socket("localhost", 4667);
                        count++;
                        clientSocket.close();
                        Thread.sleep(10);
                    }
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
    }
}

