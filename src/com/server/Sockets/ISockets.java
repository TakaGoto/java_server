package com.server.Sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ISockets {
    void close() throws IOException;
    OutputStream getOutputStream() throws IOException;
    InputStream getInputStream() throws IOException;
}
