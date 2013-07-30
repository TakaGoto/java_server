package com.server.Sockets;

import java.io.IOException;
import java.net.Socket;

public interface ServerSockets {
    public Sockets listen() throws IOException;
    public int getPort();
    public boolean notClosed();
}
