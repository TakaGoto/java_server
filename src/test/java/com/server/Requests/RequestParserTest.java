package com.server.Requests;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class RequestParserTest {
    ByteArrayInputStream inputStream;
    MyRequestParser req;
    String test;

    @Before public void init() throws IOException {
        test = "GET / HTTP/1.0\r\nHost: localhost:5000\r\nContent-Length: 10\r\n\r\ndata=cosby";
        inputStream = new ByteArrayInputStream(test.getBytes(Charset.forName("utf-8")));
        req = new MyRequestParser(inputStream);
    }

    @Test public void parseStatusLine() throws IOException {
        assertEquals("GET", req.getStatusLine().get("Method"));
        assertEquals("/", req.getStatusLine().get("Request-URI"));
        assertEquals("HTTP/1.0", req.getStatusLine().get("HTTP-Version"));
    }

    @Test public void parseStatusLinePutsAllTogether() throws IOException {
        assertEquals("GET", req.getStatusLine().get("Method"));
        assertEquals("/", req.getStatusLine().get("Request-URI"));
        assertEquals("HTTP/1.0", req.getStatusLine().get("HTTP-Version"));
        assertEquals("GET / HTTP/1.0", req.getStatusLine().get("status-line"));
    }

    @Test public void parseMessageBorder() throws IOException {
        Hashtable<String, Object> body = (Hashtable<String, Object>) req.getMessageBody().get("Body");
        assertEquals("61", req.getMessageBody().get("Content-Length"));
        assertEquals("cosby", body.get("data"));
    }

    @Test public void parseMessageHeader() {
        Hashtable header = req.getMessageHeader();
        assertEquals("10", header.get("Content-Length"));
    }

    @Test public void PostWithParams() throws IOException {
        test = "GET /parameters?hello=world&second_variable=cruelWorld HTTP/1.0\r\nHost: localhost:5000\r\nContent-Length: 10\r\n\r\ndata=cosby";
        inputStream = new ByteArrayInputStream(test.getBytes(Charset.forName("utf-8")));
        req = new MyRequestParser(inputStream);
        Hashtable<String, String> params = (Hashtable<String, String>) req.getStatusLine().get("Parameters");
        assertEquals("/parameters", req.getStatusLine().get("Request-URI"));
        assertEquals("world\r\n", params.get("hello"));
        assertEquals("cruelWorld\r\n", params.get("second_variable"));
    }
}
