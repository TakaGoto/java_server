package com.server.Requests;

import java.io.IOException;
import java.util.Hashtable;

public interface IRequestParsers {
    public void parseStatusLine() throws IOException;
    public void parseMessageHeader() throws IOException;
    public Hashtable<String, Object> getStatusLine();
    public Hashtable<String, Object> getMessageHeader();
    public Hashtable<String, Object> getMessageBody();
}
