package com.server.Mocks;

import com.server.Sockets.Sockets;

import java.io.*;
import java.nio.charset.Charset;

public class MockSocket implements Sockets {
    public ByteArrayOutputStream out;
    public ByteArrayInputStream in;

    public void close() {
    }

    public OutputStream getOutputStream() throws IOException {
        out = new ByteArrayOutputStream();
        return out;
    }

    public InputStream getInputStream() throws IOException {
        in = new ByteArrayInputStream("GET / HTTP/1.0\r\nContent-Type: text/html\r\n\r\n".getBytes(Charset.forName("utf-8")));
        return in;
    }
}
