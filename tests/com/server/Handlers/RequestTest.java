package com.server.Handlers;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import static junit.framework.Assert.assertEquals;

public class RequestTest {
    @Test public void readsTheInputStream() throws IOException {
        InputStream in = new InputStream() {
            @Override public int read() throws IOException {
                return 0;
            }
        };

        in.read("Hello World".getBytes(Charset.forName("utf-8")));
        Request req = new Request(in);
        InputStreamReader reader = new InputStreamReader(in);
        assertEquals(reader.getClass(), req.getInputStream().getClass());
    }

    @Test public void hasStatusLine() throws IOException {
        String test = "GET / HTTP/1.0\r\nHost: localhost:5000\r\nContent-Length: 10\r\n\r\ndata=cosby";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(test.getBytes(Charset.forName("utf-8")));
        Request req = new Request(inputStream);
        req.getStatusLine();
        assertEquals("GET", req.getField("Method"));
        assertEquals("/", req.getField("Request-URI"));
        assertEquals("HTTP/1.0", req.getField("HTTP-Version"));
        assertEquals("GET / HTTP/1.0", req.getField("status-line"));
    }
}
