package com.server.Handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class Request implements Requests {
    InputStreamReader reader;
    RequestParser parser;
    Hashtable<String, Object> req = new Hashtable<String, Object>();

    public Request(InputStream in) {
        reader =  new InputStreamReader(in);
        parser = new RequestParser(reader);
    }

    public InputStreamReader getInputStream() {
        return reader;
    }

    public void getStatusLine() throws IOException {
        parser.parseStatusLine();
        req.putAll(parser.getStatusLine());
    }

    public String getField(String method) {
        return (String) req.get(method);
    }
}
