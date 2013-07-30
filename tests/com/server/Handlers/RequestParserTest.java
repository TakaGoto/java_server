package com.server.Handlers;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class RequestParserTest {
    @Test public void parseHeader() throws IOException {
        RequestParser req = new RequestParser();
        String test = "GET / HTTP/1.0\r\nHost: localhost:5000\r\n\r\nBoku wa Kuma!";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(test.getBytes(Charset.forName("utf-8")));
        Hashtable<String, String> requestHeader = req.parseHeader(inputStream);
        assertEquals("GET", requestHeader.get("Method"));
        assertEquals("/", requestHeader.get("Request-URI"));
        assertEquals("HTTP/1.0", requestHeader.get("HTTP-Version"));
        assertEquals("localhost:5000", requestHeader.get("Host"));
    }
}
