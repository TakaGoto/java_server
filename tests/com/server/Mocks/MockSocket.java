package com.server.Mocks;

import com.server.Sockets.Sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MockSocket implements Sockets {
    public void close() {
    }

    public OutputStream getOutputStream() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public InputStream getInputStream() throws IOException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
