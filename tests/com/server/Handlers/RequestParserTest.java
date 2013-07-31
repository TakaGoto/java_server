package com.server.Handlers;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class RequestParserTest {
    ByteArrayInputStream inputStream;
    RequestParser req;

    @Before public void init() {
        req = new RequestParser();
    }
    @Test public void parseHeader() throws IOException {
        String test = "GET / HTTP/1.0\r\nHost: localhost:5000\r\n\r\nBoku wa Kuma!";
        inputStream = new ByteArrayInputStream(test.getBytes(Charset.forName("utf-8")));
        Hashtable<String, Object> requestHeader = req.parseHeader(inputStream);
        assertEquals("GET", requestHeader.get("Method"));
        assertEquals("/", requestHeader.get("Request-URI"));
        assertEquals("HTTP/1.0", requestHeader.get("HTTP-Version"));
        assertEquals("localhost:5000", requestHeader.get("Host"));
    }

    @Test public void parseParam() throws IOException {
        String test = "POST / HTTP/1.1\r\nHost: localhost:5000\r\nContent-Length: 10\r\n\r\ndata=cosby";
        inputStream = new ByteArrayInputStream(test.getBytes(Charset.forName("utf-8")));
        Hashtable<String, Object> request = req.parseHeader(inputStream);
        Hashtable<String, Object> body = (Hashtable<String, Object>) request.get("Body");
        assertEquals("cosby", body.get("data"));
    }
}
