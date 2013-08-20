package com.server.Requests;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class Request {
    IRequestParsers parser;
    Hashtable req = new Hashtable();

    public Request(IRequestParsers parser) throws IOException {
        this.parser = parser;
        getStatusLine();
        getMessageHeader();
        getMessageBody();
    }

    public Object getField(String method) {
        return req.get(method);
    }

    public Hashtable<String, Object> getReq() {
        return req;
    }

    public void getStatusLine() throws IOException {
        req.putAll(parser.getStatusLine());
    }

    public void getMessageHeader() throws IOException {
        req.putAll(parser.getMessageHeader());
    }

    public void getMessageBody() {
        req.putAll(parser.getMessageBody());
    }
}
