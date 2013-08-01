package com.server.Handlers;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class RequestParserTest {
    ByteArrayInputStream inputStream;
    InputStreamReader reader;
    RequestParser req;
    String test;

    @Before public void init() {
        test = "GET / HTTP/1.0\r\nHost: localhost:5000\r\nContent-Length: 10\r\n\r\ndata=cosby";
        inputStream = new ByteArrayInputStream(test.getBytes(Charset.forName("utf-8")));
        reader = new InputStreamReader(inputStream);
        req = new RequestParser(reader);
    }

    @Test public void parseHeader() throws IOException {
        Hashtable<String, Object> requestHeader = req.parseHeader(inputStream);
        assertEquals("GET", requestHeader.get("Method"));
        assertEquals("/", requestHeader.get("Request-URI"));
        assertEquals("HTTP/1.0", requestHeader.get("HTTP-Version"));
        assertEquals("localhost:5000", requestHeader.get("Host"));
    }

    @Test public void hasReaderInstance() {
        assertEquals(reader.getClass(), req.getReader().getClass());
    }

    @Test public void parseParam() throws IOException {
        Hashtable<String, Object> request = req.parseHeader(inputStream);
        Hashtable<String, Object> body = (Hashtable<String, Object>) request.get("Body");
        assertEquals("cosby", body.get("data"));
    }

    @Test public void parseStatusLine() throws IOException {
        req.parseStatusLine();
        assertEquals("GET", req.getStatusLine().get("Method"));
        assertEquals("/", req.getStatusLine().get("Request-URI"));
        assertEquals("HTTP/1.0", req.getStatusLine().get("HTTP-Version"));
    }

    @Test public void parseStatusLinePutsAllTogether() throws IOException {
        req.parseStatusLine();
        assertEquals("GET", req.getStatusLine().get("Method"));
        assertEquals("/", req.getStatusLine().get("Request-URI"));
        assertEquals("HTTP/1.0", req.getStatusLine().get("HTTP-Version"));
        assertEquals("GET / HTTP/1.0", req.getStatusLine().get("status-line"));
    }
}
