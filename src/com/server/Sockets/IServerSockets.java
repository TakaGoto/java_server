package com.server.Sockets;

import java.io.IOException;
import java.net.Socket;

public interface IServerSockets {
    public ISockets listen() throws IOException;
    public int getPort();
    public boolean notClosed();
}
