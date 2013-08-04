package com.server.Requests;

import com.server.Mocks.MockRequestParser;
import com.server.Requests.Request;
import com.server.Requests.RequestParsers;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class RequestTest {
    String test;
    Request req;
    RequestParsers parser;
    ByteArrayInputStream inputStream;

    @Before public void init() throws IOException {
        test = "GET / HTTP/1.0\r\nHost: localhost:5000\r\nContent-Length: 10\r\n\r\ndata=cosby";
        inputStream = new ByteArrayInputStream(test.getBytes(Charset.forName("utf-8")));
        parser = new MockRequestParser(inputStream);
        req = new Request(parser);
    }

    @Test public void hasStatusLine() throws IOException {
        assertEquals("GET", req.getField("Method"));
        assertEquals("/", req.getField("Request-URI"));
        assertEquals("HTTP/1.0", req.getField("HTTP-Version"));
        assertEquals("GET / HTTP/1.0", req.getField("status-line"));
    }

    @Test public void hasMessageHeader() throws IOException {
        assertEquals("61", req.getField("Content-Length"));
    }

    @Test public void hasMessageBody() throws IOException {
        Hashtable<String, String> body = (Hashtable<String, String>) req.getField("Body");
        assertEquals("cosby", body.get("data"));
    }

    @Test public void hasAllResponseFields() {
        Hashtable<String, Object> request = req.getReq();
        Hashtable<String, String> body = (Hashtable<String, String>) req.getField("Body");
        assertEquals("cosby", body.get("data"));
        assertEquals("61", req.getField("Content-Length"));
        assertEquals("GET", req.getField("Method"));
        assertEquals("/", req.getField("Request-URI"));
        assertEquals("HTTP/1.0", req.getField("HTTP-Version"));
        assertEquals("GET / HTTP/1.0", req.getField("status-line"));
    }
}
