package com.server.Mocks;

import com.server.Sockets.ISockets;

import java.io.*;
import java.nio.charset.Charset;

public class MockSocket implements ISockets {
    public ByteArrayOutputStream out;
    public ByteArrayInputStream in;
    public int closedCount = 0;

    public MockSocket(){}

    public MockSocket(String localhost, int port) {
    }

    public void close() {
        closedCount++;
    }

    public OutputStream getOutputStream() throws IOException {
        out = new ByteArrayOutputStream();
        return out;
    }

    public InputStream getInputStream() {
        in = new ByteArrayInputStream("GET / HTTP/1.0\r\nContent-Type: text/html\r\n\r\n".getBytes(Charset.forName("utf-8")));
        return in;
    }
}
