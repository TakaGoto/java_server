package com.server.Handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class Request {
    InputStreamReader reader;
    RequestParser parser;
    Hashtable<String, Object> req = new Hashtable<String, Object>();

    public Request(InputStream in) throws IOException {
        reader =  new InputStreamReader(in);
        parser = new RequestParser(reader);
        getStatusLine();
        getMessageHeader();
        getMessageBody();
    }

    public void getStatusLine() throws IOException {
        req.putAll(parser.getStatusLine());
    }

    public Object getField(String method) {
        return req.get(method);
    }

    public Hashtable<String, Object> getReq() {
        return req;
    }

    public void getMessageHeader() throws IOException {
        req.putAll(parser.getMessageHeader());
    }

    public void getMessageBody() {
        req.putAll(parser.getMessageBody());
    }
}
