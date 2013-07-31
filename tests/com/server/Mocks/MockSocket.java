package com.server.Mocks;

import com.server.Sockets.Sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MockSocket implements Sockets {
    public OutputStream out = null;
    public InputStream in = null;

    public void close() {
    }

    public OutputStream getOutputStream() throws IOException {
        return out;
    }

    public InputStream getInputStream() throws IOException {
        return in;
    }
}
